# CatFact - Fatos sobre gatos

---

## sobre

##### CatFact é um app que vai te exibir um fato aleatório sobre os gatos através de um simples clique de um botão, e caso você gosta do fato apresentado, poderá salvar esse fato e consulta-lo depois no histórico

---

## ScreenShots

#### smartphone

<div style="display: flex; overflow-x: auto; gap: 10px; padding: 10px;">
  <img src="ImageLayout/image1.JPEG" alt="Imagem 1" width="200">
  <img src="ImageLayout/image2.JPEG" alt="Imagem 2" width="200">
  <img src="ImageLayout/image3.JPEG" alt="Imagem 3" width="200">
  <img src="ImageLayout/image4.JPEG" alt="Imagem 4" width="200">
  <img src="ImageLayout/image5.JPEG" alt="Imagem 5" width="200">
</div>

#### Tablet (largura >= 600dp)

<div style="display: flex; overflow-x: auto; gap: 10px; padding: 10px;">
  <img src="ImageLayout/imageTablet1.PNG" alt="Imagem 1" width="200">
  <img src="ImageLayout/imageTablet2.PNG" alt="Imagem 2" width="200">
  <img src="ImageLayout/imageTablet3.PNG" alt="Imagem 3" width="200">
</div>

---

## Tecnologias usadas no projeto

- Android Sdk 

- kotlin 

- Room 

- Retrofit

- RecyclerView 

- Fragments

- MVVM

---

## Avisos

- A tradução aplicada no titulo e aos botões são feitos por meio do arquivo strings.xml.  Já as traduções das respostas da API, que originalmente esta em ingles, é feita utilizando o idioma padrão do sistema com "val translate = locale.getDefault().language".

- Atualmente os idiomas disponiveis no arquivo strings.xml são português e inglês, caso o idioma do seu smartphone android esteja configurado para outro idioma, apenas o conteudo vindo da API sera traduzido, entretanto, o idioma da interface (titulo e botões), permanecerão padrão, que é em português.

- Os textos salvos no histórico são armazenados de acordo com o idioma ativo no momento.  Portanto, se o usuário alterar o idioma do dispositivo depois, os textos do histórico não serão atualizados para o novo idioma.
