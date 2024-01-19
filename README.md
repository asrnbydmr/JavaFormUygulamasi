# Ders, Öğrenci ve Öğretim Görevlisi Kayıt Uygulaması

* Bu proje, ders, öğrenci ve öğretim görevlisi kayıt projesidir.
* Eclipse IDE ile yapılmıştır.
* Eclipse Marketplace ile WindowBuilder yüklenmiştir.
* Projeye harici olarak json-simple-1.1.1.jar eklenmiştir.
* Projede 3 package (default package ile 4 package), 4 form, 6 sınıf (form sınıfları ile birlikte 9 sınıf) bulunmaktadır.

## Kullanılan Kütüphaneler

### Java SDK Kütüphaneleri
- java.io
- java.util

### WindowBuilder Kütüphaneleri
- java.awt
- java.awt.event
- javax.swing

### json-simple-1.1.1.jar Kütüphaneleri
- org.json.simple
- org.json.simple.parser

## Nasıl Çalışır

1. Default package kısmında bulunan mainForm çalıştırılır.
2. Ders eklemek için açılan pencerede Course Form'a tıklanır.
3. Öğrenci eklemek için açılan pencerede Student Form'a tıklanır.
4. Öğretim görevlisi eklemek için açılan pencerede Teacher Form'a tıklanır.
* Not: İlk önce ders eklenir sonra öğrenci ve öğretim görevlisi eklenir. Ders eklemeden öğrenci ve öğretim görevlisi ekleme penceresi açılmaz. 
5. Ders eklemek için tüm alanlar doldurulur. Tüm alanlar doldurulmaz ise işlem gerçekleştirilmez.
6. Öğrenci eklemek için tüm alanlar doldurulur. Tüm alanlar doldurulmaz ise işlem gerçekleştirilmez.
7. Öğretim görevlisi eklemek için tüm alanlar doldurulur. Tüm alanlar doldurulmaz ise işlem gerçekleştirilmez.

## Packageler

- Default package: mainForm sınıfının bulunduğu pakettir.
- Apppackage: Dosya yollarını ve uygulama ayarlarını tutmak için yazılan sınıfların bulunduğu pakettir.
- Classpackage: Ders, öğrenci, öğretim görevlisi ve JSON işlemleri için yazılan sınıfların bulunduğu pakettir.
- Formpackage: studentForm, courseForm ve teacherForm sınıflarının bulunduğu pakettir.

## Formlar

- mainForm: Menü penceresidir.
- courseForm: Ders ekleme penceresidir.
- studentForm: Öğrenci ekleme penceresidir.
- teacherForm: Öğretim görevlisi ekleme penceresidir.

## Sınıflar

- apppackage -> filenameClass: Dosya isimlerini tutan sınıftır.
- apppackage -> settingsClass: Uygulama ayarlarını tutan sınıftır. 

- classpackage -> courseClass: Ders bilgisi alanlarını tutan, veri kontrolü yapan ve ders kaydı yapan sınıftır.
- classpackage -> jsonClass: JSON belgelerini kontrol eden, okuma ve yazma işlemi yapan sınıftır.
- classpackage -> studentClass: Öğrenci bilgisi alanlarını tutan, veri kontrolü yapan ve öğrenci kaydı yapan sınıftır.
- classpackage -> teacherClass: Öğretim görevlisi bilgisi alanlarını tutan, veri kontrolü yapan ve öğretim görevlisi kaydı yapan sınıftır.

- mainForm, courseForm, studentForm ve teacherForm sınıfları GUI sınıflarıdır.

## Alanlar

- filenameClass -> fileNameCourses: Ders bilgilerini tutan JSON dosyasının bilgisini tutan alandır.
- filenameClass -> fileNameStudents: Öğrenci bilgilerini tutan JSON dosyasının bilgisini tutan alandır.
- filenameClass -> fileNameTeachers: Öğretim görevlisi bilgilerini tutan JSON dosyasının bilgisini tutan alandır.

- settingsClass -> studentFormEnabled: Öğrenci penceresinin görünürlük bilgisini tutan alandır.
- settingsClass -> teacherFormEnabled: Öğretim görevlisi penceresinin görünürlük bilgisini tutan alandır.

- courseClass -> courseCode: Ders kodu bilgisini tutan alandır.
- courseClass -> courseName: Ders ad bilgisini tutan alandır.
- courseClass -> coursePeriod: Ders dönem bilgisini tutan alandır.
- courseClass -> courseCredit: Ders kredi bilgisini tutan alandır.
- courseClass -> courseQuota: Ders kota bilgisini tutan alandır.

- studentClass -> studentCode: Öğrenci numara bilgisini tutan alandır.
- studentClass -> studentName: Öğrenci ad bilgisini tutan alandır.
- studentClass -> studentSurname: Öğrenci soyad bilgisini tutan alandır.
- studentClass -> studentSection: Öğrenci bölüm bilgisini tutan alandır.
- studentClass -> studentPeriod: Öğrenci dönem bilgisini tutan alandır.
- studentClass -> studentCourse: Öğrenci ders bilgisini tutan alandır.

- teacherClass -> teacherCode: Öğretim görevlisi numara bilgisini tutan alandır.
- teacherClass -> teacherName: Öğretim görevlisi ad bilgisini tutan alandır.
- teacherClass -> teacherSurname: Öğretim görevlisi soyad bilgisini tutan alandır.
- teacherClass -> teacherSection: Öğretim görevlisi bölüm bilgisini tutan alandır.
- teacherClass -> teacherCourse: Öğretim görevlisi ders bilgisini tutan alandır.

## Diziler

- courseClass -> courses: Ders bilgilerini tutan dizidir.

- studentClass -> students: Öğrenci bilgilerini tutan dizidir.

- teacherClass -> teachers: Öğretim görevlisi bilgilerini tutan dizidir.

## Metodlar

- settingsClass -> openStudentForm: Öğrenci penceresinin görünürlüğünü ayarlamak için kullanılan metoddur. filenameClass sınıfında bulunan fileNameCourses alanı içerisinde tutulan dosya ismini veya yolunu arar. Dosya var ise studentFormEnabled değişken değerine true, yok ise değişken değerine false değeri atar.

- settingsClass -> openTeacherForm: Öğretim görevlisi penceresinin görünürlüğünü ayarlamak için kullanılan metoddur. filenameClass sınıfında bulunan fileNameTeachers alanı içerisinde tutulan dosya ismini veya yolunu arar. Dosya var ise teacherFormEnabled değişken değerine true, yok ise değişken değerine false değeri atar.

- courseClass -> courseExist: Kayıt edilmek istenilen ders bilgisinin önceden kayıt edilip edilmediğini kontrol eder.
- courseClass -> addCourse: JSON belgesine ders verilerini kaydeder.
- courseClass -> control: Ders penceresinden gelen verilerin boş (null) olup olmadığını kontrol eder.
- courseClass -> loadCourses: Belirtilen JList nesnesine ders bilgilerini ekler.
- courseClass -> searchCourses: Belirtilen arama kelimesi veya cümlesi ders kayıtlarında var mı yok mu kontrol eder. Belirtilen veri var ise belirtilen JList nesnesine ders bilgilerini ekler.

- jsonClass -> fileExists: JSON belgesinin olup olmadığını kontrol eder.
- jsonClass -> readFile: JSON belgesinin içerisindeki verileri okumak için kullanılır.
- jsonClass -> writeFile: JSON belgesine veri kayıt etmek için kullanılır.

- studentClass -> studentExist: Kayıt edilmek istenilen öğrenci bilgisinin önceden kayıt edilip edilmediğini kontrol eder.
- studentClass -> addStudent: JSON belgesine öğrenci verilerini kaydeder.
- studentClass -> control: Öğrenci penceresinden gelen verilerin boş (null) olup olmadığını kontrol eder.
- studentClass -> fillComboBox: Öğrenci penceresinde bulunan JComboBox nesnesine ders adı bilgilerini ekler.
- courseClass -> loadStudents: Belirtilen JList nesnesine öğrenci bilgilerini ekler.
- courseClass -> searchStudents: Belirtilen arama kelimesi veya cümlesi öğrenci kayıtlarında var mı yok mu kontrol eder. Belirtilen veri var ise belirtilen JList nesnesine öğrenci bilgilerini ekler.

- studentClass -> teacherExist: Kayıt edilmek istenilen öğretim görevlisi bilgisinin önceden kayıt edilip edilmediğini kontrol eder.
- studentClass -> addTeacher: JSON belgesine öğretim görevlisi verilerini kaydeder.
- studentClass -> control: Öğretim görevlisi penceresinden gelen verilerin boş (null) olup olmadığını kontrol eder.
- studentClass -> fillComboBox: Öğretim görevlisi penceresinde bulunan JComboBox nesnesine ders adı bilgilerini ekler.
- courseClass -> loadTeachers: Belirtilen JList nesnesine öğretim görevlisi bilgilerini ekler.
- courseClass -> searchTeachers: Belirtilen arama kelimesi veya cümlesi öğretim görevlisi kayıtlarında var mı yok mu kontrol eder. Belirtilen veri var ise belirtilen JList nesnesine öğretim görevlisi bilgilerini ekler.

* Not: Ders, öğrenci ve öğretim görevlisi kaydetme işlemleri kayıt buttonu (btnSave) ile gerçekleştirilir.
* Not: Öğrenci penceresinde bulunan JComboBox nesnesinin değerleri, öğrenci penceresi açıldığında veya öğrenci penceresinde bulunan Refresh Course buttonu (btnRefresh) ile comboStudentCourse nesnesine eklenir.
* Not: Öğretim görevlisi penceresinde bulunan JComboBox nesnesinin değerleri, öğretim görevlisi penceresi açıldığında veya öğretim görevlisi penceresinde bulunan Refresh Course buttonu (btnRefresh) ile comboTeacherCourse nesnesine eklenir.