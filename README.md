# Tes Magang Kampus Merdeka MSIB 7 PT. SEI

- **Nama**: Luthfi Novra 
- **Kampus**: Universitas Telkom

# Requirements
- Java JDK 21
- Java Spring Boot 3.3.2
- MySQL 8.^

# Dependencies
- Spring Data JPA
- Spring Web
- MySQL JDBC Driver
- Lombok

# Dokumentasi API

[Link Postman](https://www.postman.com/mission-meteorologist-39284478/workspace/public-api/request/20639724-e88eb05d-19be-47a3-aa80-9880ce0f5bcb)

# Spesifikasi API

## API Lokasi

### Mendapatkan Semua Data Lokasi
**Endpoint:** `GET /api/lokasi`

**Response Body**
```json
{
  "status": 200,
  "message": "Data lokasi berhasil diambil",
  "data": [
    "totalElements": 10,
    "totalPages": 1,
    "size": 10,
    "content":[
        {
          "id": 1,
          "namaLokasi": "Lokasi A",
          "negara": "Indonesia",
          "provinsi": "Jawa Barat",
          "kota": "Bandung",
          "createdAt": "2024-08-18T10:00:00Z"
        }
        ...
    ],
    "pagination": {
      "currentPage": 0,
      "totalItems": 10,
      "totalPages": 1
    }
}
```

### Create Data Lokasi
**Endpoint:** `POST /api/lokasi`

**Request Body**
```json
{
  "namaLokasi": "Lokasi A",
  "negara": "Indonesia",
  "provinsi": "Jawa Barat",
  "kota": "Bandung"
}
```

**Response Body (Success)**
```json
{
    "status": 201,
    "message": "Lokasi has been created successfully",
    "data": {
        "id": 11,
        "namaLokasi": "Lokasi Input Postman",
        "negara": "Indonesia",
        "provinsi": "Jawa Barat",
        "kota": "Bandung"
    }
}
```

**Response Body Empty Field (Failed)**
```json
{
    "status": 400,
    "message": "Validation failed",
    "data": {
        "negara": "size must be between 1 and 255"
    }
}
```

### Update Data Lokasi
**Endpoint:** `PUT /api/lokasi/{id}`

**Request Body**
```json
{
  "namaLokasi": "Lokasi C Ubah",
  "negara": "Indonesia",
  "provinsi": "Jawa Timur",
  "kota": "Surabaya"
}
```

**Response Body (Success)**
```json
{
    "status": 200,
    "message": "Lokasi updated successfully",
    "data": {
        "id": 3,
        "namaLokasi": "Lokasi C Ubah",
        "negara": "Indonesia",
        "provinsi": "Jawa Barat",
        "kota": "Bandung"
    }
}
```

**Response Invalid Id (Failed)**
```json
{
    "status": 404,
    "message": "Lokasi not found with id: 1000"
}
```
**Response Invalid Data Format (Failed)**
```json
{
    "status": 400,
    "message": "Validation failed",
    "data": {
        "kota": "must not be blank"
    }
}
```

### Delete Data Lokasi
**Endpoint:** `DELETE /api/lokasi/{id}`

**Response Body (Success)**
```json
{
    "status": 200,
    "message": "Proyek deleted successfully"
}
```

**Response Body Invalid Id (Failed)**
```json
{
    "status": 404,
    "message": "Proyek not found with id: 1000"
}
```

## API Proyek

### Mendapatkan Semua Data Proyek
**Endpoint:** `GET /api/proyek`
**Response Body**
```json
{
  "status": 200,
  "message": "Proyek retrieved successfully",
  "data": {
    "totalElements": 10,
    "totalPages": 1,
    "size": 10,
    "content": [
      {
        "id": 1,
        "namaProyek": "Project 1",
        "client": "Client A",
        "tglMulai": "2024-01-01T00:00:00",
        "tglSelesai": "2024-06-30T23:59:59",
        "pimpinanProyek": "Leader 1",
        "keterangan": "Description 1",
        "proyekLokasiSet": [
          {
            "id": {
              "proyekId": 1,
              "lokasiId": 1
            },
            "lokasi": {
              "id": 1,
              "namaLokasi": "Location 1",
              "negara": "Country A",
              "provinsi": "Province X",
              "kota": "City M"
            }
          },
          {
            "id": {
              "proyekId": 1,
              "lokasiId": 2
           ........
        ]
      },
      ........
    ],
  },
  "pagination": {
    "currentPage": 0,
    "totalItems": 10,
    "totalPages": 1
  }
}
```

### Create Data Proyek
**Endpoint:** `POST /api/proyek`

**Request Body**
```json
{
  "namaProyek": "New Project",
  "client": "Client A",
  "tglMulai": "2024-08-20T10:00:00",
  "tglSelesai": "2024-09-20T10:00:00",
  "pimpinanProyek": "John Doe",
  "keterangan": "Project Description",
  "lokasiIds": [2, 3]
}
```

**Response Body (Success)**
```json
{
    "status": 201,
    "message": "Proyek has been created successfully",
    "data": {
        "id": 11,
        "namaProyek": "New Project",
        "client": "Client A",
        "tglMulai": "2024-08-20T10:00:00",
        "tglSelesai": "2024-09-20T10:00:00",
        "pimpinanProyek": "John Doe",
        "keterangan": "Project Description",
        "proyekLokasiSet": [
            {
                "id": {
                    "proyekId": 11,
                    "lokasiId": 2
                },
                "lokasi": {
                    "id": 2,
                    "namaLokasi": "Location 2",
                    "negara": "Country B",
                    "provinsi": "Province Y",
                    "kota": "City N"
                }
            },
        ......................
```

**Response Body Invalid Data Format (Failed)**
```json
{
    "status": 400,
    "message": "Validation failed",
    "data": {
        "client": "size must be between 1 and 255"
    }
}
```

**Response Body Lokasi Not Found (Failed)**
```json
{
    "status": 404,
    "message": "Lokasi not found with id: 9999"
}
```

### Update Data Proyek
**Endpoint:** `PUT /api/proyek/{id}`

**Request Body**
```json
{
    "namaProyek": "Proyek 10 Ubah",
    "client": "Client A",
    "tglMulai": "2024-08-20T10:00:00",
    "tglSelesai": "2024-09-20T10:00:00",
    "pimpinanProyek": "John Doe",
    "keterangan": "Ubah Data Lokasi",
    "lokasiIds": [6, 7]
}
```


**Response Body (Success)**
```json
{
    "status": 200,
    "message": "Proyek updated successfully",
    "data": {
        "id": 10,
        "namaProyek": "Proyek 10 Ubah",
        "client": "Client A",
        "tglMulai": "2024-08-20T10:00:00",
        "tglSelesai": "2024-09-20T10:00:00",
        "pimpinanProyek": "John Doe",
        "keterangan": "Ubah Data Lokasi",
        "proyekLokasiSet": [
            {
                "id": {
                    "proyekId": 10,
                    "lokasiId": 6
                },
                "lokasi": {
                    "id": 6,
                    "namaLokasi": "Location 6",
                    "negara": "Country C",
                    "provinsi": "Province Z",
                    "kota": "City R"
                }
            },
      ......................
```

**Response Body Invalid Lokasi Id (Failed)**
```json
{
    "status": 404,
    "message": "Lokasi not found with id: 1000"
}
```
**Response Body Empty Body (Failed)**
```json
{
    "status": 400,
    "message": "Request body is missing or invalid"
}
```

### Delete Data Proyek
**Endpoint:** `DELETE /api/proyek/{id}`

**Response Body (Success)**
```json
{
    "status": 200,
    "message": "Proyek deleted successfully"
}
```

**Response Body Invalid Id (Failed)**
```json
{
    "status": 404,
    "message": "Proyek not found with id: 1000"
}
```