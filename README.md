# Janji

Saya Muhammad Fittra Novria Rizky dengan NIM 2411481 mengerjakan Tugas Praktikum 6 dalam mata kuliah Desain dan Pemrograman Berorientasi Objek untuk keberkahanNya maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin.

# Deskripsi Program

Program ini merupakan implementasi vidio game bernama FlappyBird yang dibuat menggunakan Java Swing GUI. Tujuan dari vidio game ini adalah player bertahan hidup selama mungkin dengan melewati pipa yang memberikan score

Fitur Program
1. Saat memulai program permainan langsung dimulai
2. Geme FlappyBird, berisini permainan utama dari game dimana player menggendalikan burung untuk menghindari pipa dan mendapatkan score sebanyak banyakanya. Score di dapatkan dari jumlah pipa yang dilewati oleh player
3. Kontrol Keybooard, player dapat menggunakan spasi untuk terbang ke atas dan bisa menggunakan R untuk restart game apabila game sudah selesai

Cara Bermain
1. Game langsung dimulai, tujuan dari game adalah mendapatkan skor sebanyak banyaknya dengan melewati pipa yang ada di game
2. Untuk mengontrol burung, gunkana tombol spasi untuk menaikan burung
3. wJika terjadi GameOver, user dapat mengulang permainan dengan menekan tombol R

# Alur dan Desain Program
Program terdiri 2 Bagian, yaitu:
1. FlappyBird, berisi permainan utama, plater mengendalikan burung dengan tombol spasi untuk menghindari pipa dan mendapatkan skor. Apabila menabrak pipa maka akan GameOver
2. GameOver, berisi tampilan gameover dimana game diberhentiikan dan player dapat memulai ulang kembali game dengan menekan tombol R

Penjelasan Class

Dimana Program ini terdapat 5 class, yaitu:
1. Class Player, Class ini berfungsi sebagai representasi objek pemain (burung) di game.
Ia menyimpan posisi, ukuran, kecepatan, dan gambar burung.
2. Class Pipe, Class ini murni data model — tidak punya logika game, hanya menyimpan dan menyediakan data untuk digambar & digerakkan di class Logic.
3. Class Logic, Class ini adalah otak game.
Ia mengatur semua interaksi antara pemain (Player), pipa (Pipe), dan tampilan (View).
4. Class View, Class ini adalah turunan JPanel yang digunakan di JFrame.
Ia bertugas melukis (render) setiap frame berdasarkan data dari Logic.
5. Class App, Class ini hanya berfungsi untuk menjalankan game dan menampilkan GUI.
Semua logika game dijalankan oleh Logic, dan tampilan digambar oleh View.

Alur Program
1. App.java
  Program dimulai dari method main() di class App.
  Di sini dibuat JFrame berukuran 360x640 untuk jendela game.
  Ditambahkan JLabel untuk menampilkan skor di bagian atas.
2. Logic.java
  Asset dimuat:
    background.png, bird.png, upperPipe.png, lowerPipe.png.
  Objek utama dibuat:
    Player (burung) posisi tengah layar.
    ArrayList<Pipe> kosong untuk menampung pipa-pipa.
  Timer diaktifkan:
    gameLoop update 60 kali per detik (FPS).
    pipesCooldown menambahkan pipa baru tiap 1,5 detik.
3. View.java
  Menggambar elemen visual ke layar
4. Player.java
  menyimpan data burung
5. Pipa.java
  menyimpan data pipa
6. GameLoop berjalan
  Setiap frame burung jatuh, pipa bergerak, skor bertambah
7. GameOver
  Saat tabrakan, berhenti; tampil tulisan “Game Over”
8. Restart
  Tekan R game dimulai kembali dari awal
