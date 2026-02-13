<h2 align="center">Spring Boot - Rest API</h2>

## Açıklama
Arama, ekleme, güncelleme ve silme işlemleri ile kullanıcı yönetimi için REST API.

## Nasıl Kullanılır?
1- Projeyi bilgisayarınıza <b>clone</b>'layın.<br>
2a- Eclipse uygulamasında <strong>New>Project>Java Project</strong> şeklinde ilerleyip yeni proje oluşturun.<br>
Projeyi oluşturduğunuzda otomatik olarak gelen <b>src</b> klasöründe <strong>Sağ Tık>Import>General>File System</strong> şeklinde ilerleyin.<br>
Projeyi <b>clone</b>'ladığınızda bilgisayarınızda oluşan klasörün altındaki <b>src</b> klasörünü seçip import işlemini tamamlayın.<br>
2b- IntelliJ IDEA uygulamasında <strong>File>Open</strong> şeklinde ilerleyip projeyi içe aktarın<br>
3- <b>Main.java</b> dosyasını çalıştırın.

## Endpoints
<b>POST işlemini test edebilmek için örnek cURL komutu:</b><br>
curl -X POST http://localhost:8080/users/send ^<br>
-H "Content-Type: application/json" ^<br>
-d '{"ad":"Ad Soyad","email":"Email","cevrimici":true}'
<br><br>
<b>PUT işlemini test edebilmek için örnek cURL komutu:</b><br>
curl -X PUT http://localhost:8080/users/change/{id} ^<br>
-H "Content-Type: application/json" ^<br>
-d '{"ad":"Ad Soyad","email":"Email","cevrimici":false}'
<br><br>
<b>DELETE işlemini test edebilmek için cURL komutu:</b><br>
curl -X DELETE http://localhost:8080/users/delete/{id}

## Durum
![Dosya Boyutu](https://img.shields.io/badge/11%2C3%20KB-gray?style=flat&logo=github&label=file%20size&color=green)
![GitHub Repo Boyutu](https://img.shields.io/github/repo-size/kaansahin04/SpringBoot-RestAPI?logo=github&color=green)
![Programlama Dili](https://img.shields.io/github/languages/top/kaansahin04/SpringBoot-RestAPI?color=A49410)
