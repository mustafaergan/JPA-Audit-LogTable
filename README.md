# JPA ve Hibernate Envers ile Audit (Değişiklik Kaydı)

Spring Data JPA'nın denetim alanları (`@CreatedBy`, `@CreatedDate`, `@LastModifiedBy`,
`@LastModifiedDate`) ile Hibernate Envers'in `@Audited` özelliğini birlikte gösteren örnek. Bir kayıt her
değiştiğinde o anki hali otomatik olarak `_AUD` tablosuna yazılır.

> **Not:** Bu depo 2018 yılında eğitim/örnek amaçlı yazıldı ve artık güncellenmiyor. Bağımlılıkları eskidir
> ve bilinen güvenlik açıkları içerir; üretimde kullanmayın.

## İçerik

| Yol | Ne yapar |
|---|---|
| `domain/User.java` | `@Audited` ve `@EntityListeners(AuditingEntityListener.class)` ile işaretli entity; oluşturan/değiştiren kişi ve zaman alanları, `@Version` |
| `configuration/AuditConfiguration.java` | `@EnableJpaAuditing` ile denetimi açar |
| `configuration/AuditorAwareImpl.java` | Kaydı yapan kullanıcıyı döndürür (örnekte sabit bir isim) |
| `controller/TestController.java` | `GET /test/update` aynı kaydı bir kez ekleyip iki kez günceller; böylece `_AUD` tablosunda üç revizyon oluşur |

## @Audited

`@Audited`, entity için sonuna `_AUD` eklenmiş bir log tablosu oluşturur. Örnekte bunun için şu iki
bağımlılık eklendi:

```xml
<dependency>
    <groupId>org.hibernate</groupId>
    <artifactId>hibernate-ehcache</artifactId>
</dependency>
<dependency>
    <groupId>org.hibernate</groupId>
    <artifactId>hibernate-envers</artifactId>
</dependency>
```

`_AUD` tablosundaki `REV` alanı Envers'in kendi tuttuğu revizyon numarasıdır (`REVINFO` tablosuna bağlanır),
`REVTYPE` ise değişikliğin türünü gösterir: `0` ekleme, `1` güncelleme, `2` silme.

Spring Data JPA tarafında `@CreatedBy` ve `@LastModifiedBy` alanları, `@EntityListeners(AuditingEntityListener.class)`
ve bir `AuditorAware` yapılandırmasıyla otomatik doldurulur. Gerçek bir uygulamada `AuditorAwareImpl`,
oturum açmış kullanıcıyı (ör. Spring Security'den) döndürecek şekilde güncellenebilir.

## Teknolojiler

- Java 8, Spring Boot 2.0.4 (Web, Data JPA, Validation)
- Hibernate Envers, H2 veritabanı (dosya tabanlı)
- Maven

## Çalıştırma

```bash
mvn spring-boot:run
```

1. `http://localhost:8080/test/update` adresini açın.
2. `http://localhost:8080/h2` adresinden H2 konsoluna girin. JDBC adresi ve kullanıcı bilgileri
   `src/main/resources/application.properties` dosyasındadır; veritabanı ev dizininizde `test` adlı
   dosya olarak oluşur.
3. `USER` ve `USER_AUD` tablolarını karşılaştırın.
