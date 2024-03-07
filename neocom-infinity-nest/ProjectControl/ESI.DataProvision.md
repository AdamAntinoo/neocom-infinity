# ESI Data Provision
## Get a Character ID
curl -X POST "https://esi.evetech.net/latest/universe/ids/?datasource=tranquility&language=en" -H "accept: application/json" -H "Accept-Language: en" -H "Content-Type: application/json" -H "Cache-Control: no-cache" -d "[ \"Perico Tuerto\"]"

{
  "characters": [
    {
      "id": 93813310,
      "name": "Perico Tuerto"
    }
  ]
}
## Gen Mining progress data
characters/93813310/mining/?datasource=tranquility&page=1

[
  {
    "date": "2024-02-23",
    "quantity": 210,
    "solar_system_id": 30003541,
    "type_id": 17453
  },
  {
    "date": "2024-02-23",
    "quantity": 34243,
    "solar_system_id": 30003538,
    "type_id": 17464
  },
  {
    "date": "2024-02-23",
    "quantity": 3073,
    "solar_system_id": 30003538,
    "type_id": 1224
  },
  {
    "date": "2024-02-23",
    "quantity": 10288,
    "solar_system_id": 30003538,
    "type_id": 17459
  }
]
