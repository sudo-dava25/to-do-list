# ToDoApp

Aplikasi To-Do List sederhana berbasis Android yang dibuat menggunakan Java di AIDE IDE.

---

## Fitur

- Tambah tugas baru
- Hapus tugas
- Tandai tugas selesai (checkbox + teks dicoret)
- Simpan data secara permanen (SQLite)
- Kategori prioritas: Tinggi, Sedang, Rendah

---

## Struktur Project

```
app/src/main/
├── java/com/todolist/app/
│   ├── MainActivity.java
│   ├── Task.java
│   ├── TaskAdapter.java
│   └── DatabaseHelper.java
└── res/
    ├── layout/
    │   ├── activity_main.xml
    │   ├── item_task.xml
    │   └── dialog_add_task.xml
    └── values/
        └── strings.xml
```

---

## Teknologi

| Komponen | Keterangan |
|---|---|
| Bahasa | Java |
| IDE | AIDE (Android) |
| Database | SQLite |
| UI | RecyclerView + Material Design |
| Min SDK | 21 (Android 5.0) |
| Target SDK | 34 (Android 14) |

---

## Cara Menjalankan

1. Clone repository ini
   ```
   git clone https://github.com/sudo-dava25/ToDoApp.git
   ```
2. Buka AIDE di Android
3. Pilih Open project from folder
4. Arahkan ke folder hasil clone
5. Klik Build & Run

---

## Dependencies

```groovy
implementation 'androidx.appcompat:appcompat:1.6.1'
implementation 'androidx.recyclerview:recyclerview:1.3.0'
implementation 'com.google.android.material:material:1.9.0'
implementation 'androidx.coordinatorlayout:coordinatorlayout:1.2.0'
```

---

## Screenshot

Tambahkan screenshot aplikasi di sini.

---

## Developer

- Nama: dava25
- Package: com.todolist.app
- Versi: 1.0

---

## Lisensi

Project ini dibuat untuk keperluan belajar.
