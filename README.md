**Nama**: **Ghina Nabila Gunawan**

**NPM**: **2206825914** 

**Kelas**: **Advpro - A**

**Kode Asdos**: **RFL**

# 🚀 Tutorial 1️⃣

### Reflection 1

<details>
  <summary>Click to Expand: Reflection 1</summary>

Dalam mengembangkan dua fitur baru di aplikasi ini, saya berusaha menerapkan prinsip **Clean Code** supaya _code_ tidak hanya bekerja dengan baik, tapi juga mudah dibaca dan dikembangkan nantinya. Menurut saya salah satu yang paling penting adalah **penamaan yang jelas dan deskriptif**.
Misalnya, saya memberi nama field seperti `nameField` dan `quantityField` yang jelas menunjukkan elemen apa yang dimaksud. Saya juga memperhatikan keterbacaan kode. 
Prinsip **Single Responsibility Principle (SRP)** juga saya terapkan di sini. Contohnya method _testing_ seperti `userCanCreateProduct` hanya berfokus pada pengujian fitur pembuatan produk.
Di bagian **validasi input**, saya pastikan _input_ yang diterima dari _user_ sudah divalidasi, seperti memastikan **jumlah produk tidak negatif**. 
Selain itu, dalam _code_ _testing_ **Selenium**, saya juga pakai `WebDriverWait` untuk menunggu elemen muncul di halaman sebelum interaksi dilakukan. Ini penting untuk menghindari error akibat elemen yang belum semuanya dimuat.
Namun, tentu ada beberapa bagian yang bisa diperbaiki. Misalnya, dalam pengaturan **baseUrl**, baiknya kalau URL tidak di-_hardcode_, tapi menggunakan properti dari file konfigurasi Spring (`application.properties` atau `application.yml`) yang mungkin akan lebih mempermudah pengelolaan URL di berbagai environment.

</details>

---

### Reflection 2.1

<details>
  <summary>Click to Expand: Reflection 2.1</summary>

Saya memilih untuk menggunakan **Spring Boot** di bagian _backend_ dan **Thymeleaf** di bagian _frontend_ karena keduanya memberikan kemudahan pengembangan.  
Dengan **Spring Boot**, saya bisa lebih fokus ke bisnis *logic* dan _testing_ tanpa khawatir konfigurasi _server_ yang rumit. Selain itu, kalau aplikasi berkembang jadi lebih besar, Spring Boot memudahkan untuk menerapkan arsitektur **microservices**, yang memungkinkan kita membangun aplikasi yang lebih _scalable_. 
Untuk bagian _frontend_, saya menggunakan **Thymeleaf** karena bisa mempermudah pengikatan data antara _backend_ dan elemen HTML. Dengan **Thymeleaf**, tampilan aplikasi jadi dinamis dan bisa diperbarui dengan mudah, karena data dari model bisa langsung dihubungkan dengan elemen HTML.

</details>

---

### Reflection 2.2

<details>
  <summary>Click to Expand: Reflection 2.2</summary>

Berikutnya, saya pilih menggunakan **Selenium WebDriver** untuk _functional testing_. Dengan Selenium, saya bisa mengotomatisasi pengujian interaksi _user_ di halaman web. Jadi, bukan hanya _backend_ yang diuji, tapi juga alur interaksi di _frontend_ yang memastikan aplikasi berjalan sesuai ekspektasi _user_.

</details>

---
