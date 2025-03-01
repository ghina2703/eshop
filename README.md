**Nama**    : **Ghina Nabila Gunawan**

**NPM**     : **2206825914** 

**Kelas**   : **AdvProg - A**

**Kode Asdos**  : **RFL**

**Link Koyeb ADV Shop**  : https://ridiculous-debor-ghina27-238eb2a3.koyeb.app/product/list

**Link Koyeb Car**  : https://ridiculous-debor-ghina27-238eb2a3.koyeb.app/car/listCar 

# 🚀 Tutorial 3️⃣

### Reflection

<details>

  <summary>Click to Expand: 1️⃣ Prinsip SOLID yang saya terapkan dalam proyek ini</summary>

#### **SRP (Single Responsibility Principle)**
- Dalam modul ini, _class_ `CarServiceImpl` hanya bertanggung jawab atas bisnis logic terkait Car (create, find, update, dan delet Car).
- Penyimpanan data dikelola oleh `CarRepository`, jadi setiap _class_ punya tanggung jawab yang jelas.

#### **OCP (Open/Closed Principle)**
- Kode harus **terbuka untuk _extension_, tapi tertutup untuk _modification_**.
- Saya menerapkan OCP dengan menggunakan _interface_ `CarService`.
- Jika saya ingin menambahkan cara penyimpanan baru, saya bisa membuat implementasi baru tanpa mengubah kode yang sudah ada.

#### **LSP (Liskov Substitution Principle)**
- Saya membuat objek _superclass_ bisa digantikan oleh objek _subclass_ tanpa mengubah perilaku aplikasi.
- Contohnya, jika saya mengganti implementasi `ProductServiceImpl` dengan yang baru, aplikasi tetap berjalan dengan baik tanpa perubahan besar pada _main code_.

#### **DIP (Dependency Inversion Principle)**
- Saya memastikan modul tingkat tinggi (seperti `CarServiceImpl`) tidak langsung bergantung pada modul tingkat rendah (`CarRepository`).
- Saya menggunakan **_abstraction (interface)_** untuk menghubungkan `CarServiceImpl` dengan `CarRepository`, jadi implementasi penyimpanan bisa diganti tanpa mengubah logic utama.

</details>

---

<details>

  <summary>Click to Expand: 2️⃣ Keuntungan dari penerapan prinsip SOLID dalam proyek saya</summary>

#### **Lebih mudah dalam _changes_ dan _maintenance_**
Dengan **SRP** dan **OCP**, jika saya ingin mengganti penyimpanan dari _in-memory_ ke _database_, saya cukup membuat implementasi baru tanpa harus mengubah kode utama (`CarServiceImpl`).
Hal ini membuat proyek lebih fleksibel dan lebih mudah untuk dikembangkan kedepannya.

#### **Testing-Friendly**
Dengan menerapkan **DIP**, kode saya bergantung pada _abstraction (interface)_ dan bukan implementasi langsung.
Ini memungkinkan saya menggunakan _mocking_ dalam _testing_ tanpa harus bergantung pada penyimpanan asli. 
Contohnya saya bisa test `CarServiceImpl` dengan **mock dari `CarRepository`**, jadi lebih cepat dan tidak bergantung pada sistem eksternal.

#### **Memudahkan penambahan fitur baru**
Dengan **OCP**, saya bisa menambahkan fitur baru tanpa harus mengubah kode lama.
Contohnya kalau saya ingin menambahkan fitur pencatatan _log_ setiap kali Car diperbarui, saya bisa membuat _decorator_ atau _subclass_ tanpa mengubah `CarServiceImpl`.

#### **Mengurangi ketergantungan antar _class_ (Loose Coupling)**
Dengan menerapkan **DIP** dan **ISP**, saya memastikan bahwa setiap _class_ hanya bergantung pada apa yang benar-benar dibutuhkan.
Contohnya `CarServiceImpl` hanya berkomunikasi dengan `CarRepository` melalui _interface_, bukan langsung ke implementasi penyimpanan tertentu.
Ini membuat _code_ lebih fleksibel karena saya bisa mengganti penyimpanan tanpa mengubah main implementation nya.

</details>

---

<details>

  <summary>Click to Expand: 3️⃣ Kerugian Tidak Menerapkan Prinsip SOLID</summary>

---

#### **Kesulitan dalam Code Maintenance**
Tanpa **Single Responsibility Principle (SRP)**, satu _class_ bisa punya terlalu banyak tanggung jawab.
Contohnya kalau `CarServiceImpl` menangani bisnis logic sekaligus **penyimpanan data**, jadi perubahan dalam cara penyimpanan akan mempengaruhi seluruh _class_.
Akibatnya, kode lebih sulit di-maintain dan lebih rentan terhadap _bug_.

#### **Testing yang lebih sulit dan tidak fleksibel**
Tanpa **Dependency Inversion Principle (DIP)**, _class_ akan langsung bergantung pada implementasi langsung daripada _abstraction_.
Sulit untuk membuat _mocking_ dalam unit testing karena sistem tidak punya fleksibilitas untuk mengganti dependensi dengan yang lebih ringan.
Contohnya kalau `CarServiceImpl` langsung bergantung pada `CarRepository`, maka dalam testing saya harus menggunakan `CarRepository` asli, bukan _mock_ atau _fake repository_, yang membuat testing lebih kompleks.

#### **Menambah Fitur Baru Jadi Lebih Sulit**
Tanpa **Open/Closed Principle (OCP)**, setiap kali ada fitur baru, saya harus mengubah kode lama.
Hal ini bisa menyebabkan banyak ** bug yang muncul setelah perubahan**.
Contohnya kalau saya mau menambahkan **logging setiap kali Car diperbarui**, saya harus mengedit `CarServiceImpl`, bukannya cukup menambahkan _decorator_ atau _subclass_.

#### **Ketergantungan antar class (_Tight Coupling_)**
Tanpa **Liskov Substitution Principle (LSP)** dan **Interface Segregation Principle (ISP)**, _class_ jadi **terlalu saling bergantung**, yang menyulitkan perubahan atau _improvement_ bagian tertentu tanpa merusak keseluruhan sistem.
Contohnya kalau saya ingin mengganti cara penyimpanan dari _in-memory_ ke _database_, saya mungkin perlu mengubah banyak bagian kode yang sebelumnya bergantung pada implementasi tertentu.

#### **Kode yang berantakan dan banyak duplikasi**
Tanpa **SRP** dan **ISP**, banyak kode **duplikat** yang tersebar.
Dampaknya pastinya yaitu nanti akan sulit untuk menemukan dan memperbaiki bug, lalu setiap perubahan harus dilakukan di banyak tempat sekaligus, serta risiko ketidakkonsistenan data dalam aplikasi meningkat.

#### **Bergantung pada satu Implementasi**
Tanpa **DIP**, sistem terlalu bergantung pada satu sistem tertentu, membuat **perubahan atau _improvement_** jadi lebih sulit.
Contohnya kalau saya ingin mengganti cara penyimpanan dari `CarRepository` ke layanan eksternal, saya harus memodifikasi semua bagian kode yang menggunakan `CarRepository`, padahal cukup mengganti implementasi di satu tempat saja.

</details>

---

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
