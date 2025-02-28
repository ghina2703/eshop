**Nama**    : **Ghina Nabila Gunawan**

**NPM**     : **2206825914** 

**Kelas**   : **AdvProg - A**

**Kode Asdos**  : **RFL**

**Link Koyeb**  : https://ridiculous-debor-ghina27-238eb2a3.koyeb.app/product/list

**Link Koyeb**  : https://ridiculous-debor-ghina27-238eb2a3.koyeb.app/car/listCar 

# 🚀 Tutorial 3️⃣

### Reflection

<details>

  <summary>Click to Expand: 1️⃣ Prinsip SOLID yang diterapkan dalam proyek ini</summary>

#### **SRP (Single Responsibility Principle)**
- Dalam konteks proyek ini, _class_ `CarServiceImpl` hanya bertanggung jawab untuk bisnis _logic_ terkait mobil (membuat, menemukan, memperbarui, dan menghapus mobil). _Code_ ini tidak berhubungan langsung dengan penyimpanan _database_, yang dilakukan oleh `CarRepository`.
- **Implementasi**: _Class_ `CarServiceImpl` hanya menangani bisnis _operation_ mobil, sedangkan penyimpanan data dikelola oleh `CarRepository` dan _interface_ `CarService`.

#### **OCP (Open/Closed Principle)**
- Kode harus _open_ untuk _extension_ tapi tertutup untuk _modification_. Berarti kode yang sudah ada tidak perlu dimodifikasi untuk menambahkan fitur baru.
- **Implementasi**: Dengan mengimplementasikan _interface_ `CarService`, saya membuat `CarServiceImpl` _open_ untuk _extension_ (misalnya dengan menambahkan implementasi penyimpanan baru) tanpa harus memodifikasi_ class _`CarServiceImpl` itu sendiri. Contohnya, kita bisa menambahkan implementasi `InMemoryCarService` dengan mudah tanpa mengubah kode dalam `CarServiceImpl`.

#### **DIP (Dependency Inversion Principle)**
- Modul tingkat tinggi (seperti `CarServiceImpl`) tidak boleh bergantung pada modul tingkat rendah (seperti `CarRepository`). Kedua modul harus bergantung pada _abstractions_ (_interface_), bukan implementasi langsung.
- **Implementasi**: Sebelumnya, `CarServiceImpl` bergantung langsung pada `CarRepository`, yang melanggar prinsip DIP. Untuk memperbaikinya, saya menggunakan _interface_ `CarService` dan memastikan `CarServiceImpl` bergantung pada _interface_ ini, bukan implementasi langsung. Ini memungkinkan untuk mengganti cara penyimpanan tanpa mengubah logika.

#### **LSP (Liskov Substitution Principle)**
- Objek dari _superclass_ harus bisa digantikan dengan objek dari _subclass_ tanpa mempengaruhi kebenaran program.
  - **Implementasi**: LSP diterapkan dengan menggunakan class `ProductService` yang punya beberapa implementasi, misalnya ProductServiceImpl. Sebagai contoh, jika suatu saat kita perlu mengganti ProductServiceImpl dengan implementasi lain yang memenuhi kontrak yang sama (misalnya menggunakan service berbasis file atau database), kita dapat melakukannya tanpa merubah kode yang menggunakan ProductService. Hal ini memastikan bahwa aplikasi tetap bekerja dengan baik meskipun kita mengganti implementasi detailnya, selama implementasi baru tersebut sesuai dengan antarmuka ProductService.


</details>

---
<details>

  <summary>Click to Expand: 2️⃣ Keuntungan dari penerapan prinsip SOLID pada proyek ini</summary>

#### **Mudah untuk melakukan perubahan dan pemeliharaan kedepannya**
Contohnya jika kita ingin mengubah cara penyimpanan mobil (misalnya, mengganti penyimpanan in-memory dengan database), kita cukup mengganti implementasi `CarStorage` tanpa harus mengubah kode di `CarServiceImpl`. Ini membuat aplikasi lebih mudah dipelihara dan lebih fleksibel dalam menghadapi perubahan kebutuhan.

#### **Lebih mudah untuk Testing**
Kode yang mengikuti prinsip SOLID, terutama OCP dan DIP, lebih mudah untuk diuji karena bergantung pada abstractions (interface), bukan implementasi langsung, yang memungkinkan untuk menggunakan teknik seperti mock testing. Misalnya, untuk menguji `CarServiceImpl`, saya menggunakan mock dari `CarRepository` atau `CarService`, sehingga tidak perlu terhubung ke database.

#### **Bisa diperluas dengan mudah**
Kalau suatu saat kita membutuhkan fitur baru untuk mencatat log setiap kali ada Car yang diperbarui, kita cukup membuat subclass atau decorator yang mengelola logging, dan menyuntikkan logika tersebut ke dalam alur yang ada tanpa memodifikasi kode utama di `CarServiceImpl`.

#### **Meminimalkan ketergantungan**
Dengan menerapkan prinsip DIP dan ISP, bisa mengurangi ketergantungan antara kelas yang tidak perlu. Ini meminimalkan _tight coupling_ dalam kode, sehingga lebih fleksibel saat ada kebutuhan untuk mengganti komponen. 
Contohnya `CarServiceImpl` hanya bergantung pada interface `CarStorage`umum, bukan implementasi langsung, yang memudahkan penggantian interface kalau diperlukan tanpa mempengaruhi layanan utama.

</details>

---
<details>
  <summary>Click to Expand: 3️⃣ Kerugian Tidak Menerapkan SOLID Principles</summary>

- Tanpa **Single Responsibility Principle (SRP)**, kelas bisa punya banyak tanggung jawab, sehingga perubahan kecil pada satu bagian bisa mempengaruhi bagian lainnya. Ini membuat pemeliharaan kode lebih sulit dan berisiko.

- Tanpa **Dependency Inversion Principle (DIP)**, bisa membuat kesulitan untuk mengganti dependensi dalam pengujian, sehingga pengujian jadi lebih rumit dan tidak fleksibel.

- Tanpa **Open/Closed Principle (OCP)**, menambah fitur baru sering memerlukan perubahan besar pada kode yang lama, itu bisa merusak bagian lain dari aplikasi.

- Tanpa **Liskov Substitution Principle (LSP)** dan **Interface Segregation Principle (ISP)**, ketergantungan antara kelas menjadi terlalu kuat (_tight coupling_), menyulitkan penggantian atau pengembangan bagian tertentu tanpa mempengaruhi seluruh sistem.

- Tanpa **SRP** dan **ISP**, kode menjadi duplikat dan tersebar di banyak tempat, yang meningkatkan kompleksitas dan kesulitan pemeliharaan kedepannya.

- Tanpa **DIP**, jadi terikat pada implementasi langsung, yang membuat penggantian teknologi atau sistem lebih susah.

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
