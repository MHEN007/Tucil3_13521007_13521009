# Tucil3_13521007
# Shortest Path Finding using A* and UCS
## Author
| NIM | Nama Lengkap |
| --- | ------------ |
| 13521007 | Matthew Mahendra |
| 13521009 | Christophorus Dharma Winata |

## Deskripsi Singkat
Pada peta, kadang kala diperlukan rute yang terpendek agar dapat menghemat perjalanan, baik dari segi biaya maupun tenaga. Pencarian rute terpendek ini dapat menggunakan beberapa algoritma pada graf. Contoh dari algoritma tersebut adalah algoritma A* dan Uniformed Cost Search.

## Pre-requisite
Untuk menjalankan program ini, Anda memerlukan
1. Java Development Kit. Program dibuat pada versi 19.0.1
2. Maven untuk JAR packaging

## Setup Program
1. Lakukan clone repository
2. Pada `root` dari program ini, masukkan perintah `java -jar ./bin/tucil3_13521007_13521009.jar`. Jika perlu melakukan kompilasi ulang maven, masukkan perintah `mvn clean -PotherOutputDir  compile assembly:single`
3. Program dapat dijalankan

## Cara Menggunakan Program
1. Program akan meminta pengguna untuk memasukkan nama file dalam bentuk .txt dari peta yang akan dimasukkan. File yang akan dimasukkan diletakkan di folder test. File wajib mengikuti format sebagai berikut,

    1. Dimulai dengan jumlah lokasi pada peta
    2. Nama tempat dan koordinat longitude dan langitude pada peta. Nama tempat tidak menggunakan spasi
    3. Matriks ketetanggaan berbobot yang sesuai

    Untuk kasus akan menggunakan graf yang tidak terkait pada peta, maka adjacency matrix menjadi adjacency matrix berbobot, sedangkan jika terkait maka adjacency matrix berisi status ketetanggan biner (1 atau 0).

    Kasus graf yang tidak terkait harus menggunakan koordinat kartesius yang bernilai 10 s.d. 100

2. Setelahnya program akan menampilkan nama-nama lokasi yang tersedia. Pengguna kemudian memasukkan titik awal dan titik tujuan

3. Selanjutnya, pengguna memasukkan algoritma pencarian yang akan digunakan. 1 untuk UCS, 2 untuk A*

4. Program kemudian akan menampilkan rute terpendek, jarak yang ditempuh, beserta visualisasi pada peta