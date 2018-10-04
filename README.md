@Audited

_AUD şeklinde log table oluşturmaktadır. @AUDITED için iki tane dependency ihtiyacı bulunmaktadır.

_AUD tablosunda rev alanı kendi içerisinde saydığı id, revtype ilgili datanin id gore saydığı sıralama.

```
<dependency>
    <groupId>org.hibernate</groupId>
    <artifactId>hibernate-ehcache</artifactId>
</dependency>
<dependency>
    <groupId>org.hibernate</groupId>
    <artifactId>hibernate-envers</artifactId>
</dependency>
```


Spring data JPA @LastModifiedBy ve @CreatedBy içinde @EntityListeners(AuditingEntityListener.class) configuration oluşturup otomatik olarak atacak şeklinde güncellene bilir.