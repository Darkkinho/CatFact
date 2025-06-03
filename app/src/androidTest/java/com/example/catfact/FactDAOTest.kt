package com.example.catfact

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.catfact.RoomSavedList.FactDAO
import com.example.catfact.RoomSavedList.FactDataBase
import com.example.catfact.RoomSavedList.RoomFactEntity
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class FactDAOTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var factDao: FactDAO
    private lateinit var factDb: FactDataBase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        factDb = Room.inMemoryDatabaseBuilder(context, FactDataBase::class.java).
        allowMainThreadQueries().build()

        factDao = factDb.factDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        factDb.close()
    }

    @Test
    @Throws(Exception::class)
     fun writeUserAndReadInList() = runBlocking {

      val factCatId1 = RoomFactEntity(id = 1, "Cats have 3 eyelids.")
      factDao.insert(factCatId1)

      val factCatId2 = RoomFactEntity(id = 2, "A tiger's stripes are like fingerprints.")
      factDao.insert(factCatId2)

      val result = factDao.getAll().getOrAwaitValue()

      assertEquals(factCatId1, result[0])
      assertEquals(factCatId2, result[1])
    }

    fun <T> LiveData<T>.getOrAwaitValue(
        time: Long = 2,
        timeUnit: TimeUnit = TimeUnit.SECONDS
    ): T {
        var data: T? = null
        val latch = CountDownLatch(1)

        val observer = object : Observer<T> {
            override fun onChanged(value: T) {
                data = value
                latch.countDown()
                this@getOrAwaitValue.removeObserver(this)
            }
        }

        this.observeForever(observer)

        if (!latch.await(time, timeUnit)) {
            throw RuntimeException("LiveData valor não foi emitido a tempo.")
        }

        @Suppress("UNCHECKED_CAST")
        return data as T
    }

}