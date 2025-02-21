**Nama**    : **Ghina Nabila Gunawan**

**NPM**     : **2206825914** 

**Kelas**   : **AdvProg - A**

**Kode Asdos**  : **RFL**

**Link Koyeb**  : https://ridiculous-debor-ghina27-238eb2a3.koyeb.app/product/list

# 🚀 Tutorial 2️⃣

### Reflection

<details>

  <summary>Click to Expand: Poin 1</summary>

Selama pengerjaan _exercise_ ini, ada beberapa masalah _code quality_ yang berhasil saya perbaiki, yaitu:

- Saya menghapus beberapa _import_ yang tidak digunakan dalam kode, seperti `java.util.ArrayList` dan `java.util.Iterator`. Masalah ini biasanya muncul karena adanya *import* yang tidak terpakai dalam _class_ yang bisa merusak implementasi _clean code_ .

- Ada _warning_ terkait penggunaan `{}` yang hilang pada beberapa *if statements* dan _loop_. Saya menambahkan `{}` untuk meningkatkan _code readability_ dan memastikan bahwa aturan ini mengikuti _best practices_ untuk menjaga _maintainability_.

- _Warning_ di _class_ `EshopApplication` yang punya _public constructor_ walaupun hanya berisi _static method_. Karena `EshopApplication` berfungsi sebagai _main class_, saya tidak menggunakan _private constructor_, tapi saya menambahkan `@SuppressWarnings("PMD.UseUtilityClass")` untuk memastikan _class_ EshopApplication tetap bisa berfungsi sebagai _entry point_ app Spring Boot tanpa masalah dengan _utility class warning_.


</details>

---

<details>

  <summary>Click to Expand: Poin 2</summary>

Setelah mengimplementasikan _pipeline_ CI/CD, saya merasa proses yang ada sudah memenuhi definisi dari **Continuous Integration (CI)** dan **Continuous Deployment (CD)** yang diajarkan di kelas. 

- Proses **CI** di atas sudah mencakup otomatisasi dalam menjalankan *test suites* setiap kali ada perubahan kode (_push_ ke _branch_ atau _merge pull request_). Hal ini memastikan _code quality_ bisa langsung dianalisis melalui _analysis tool_ (saya menggunakan **PMD**) yang digunakan dalam _workflow_.

- Setelah _code_ berhasil melewati proses CI, _pipeline_ langsung meng-_deploy_ aplikasi ke **PaaS** (saya menggunakan **Koyeb**) dengan mekanisme _**auto-deploy**_ berbasis **Docker**. Jadi setiap perubahan kode yang lolos tes akan langsung tersedia di _automated production pipeline_, ini merupakan inti dari implementasi **CD**.

- Selain itu, saya juga menggunakan **Scorecard** untuk melakukan **_security analysis_** sebagai bagian dari proses CI, hal ini membantu untuk memastikan bahwa semua kode yang di-_deploy_ memenuhi standar keamanan tertentu.
- Saya juga memanfaatkan GitHub Actions untuk mengintegrasikan _testing_, _code analysis_, dan _auto-deployment_ untuk memastikan bahwa app selalu siap untuk otomatis di-_deploy_ ke server produksi setiap kali ada perubahan yang di-_push_ ke _repo_.

</details>

---

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
