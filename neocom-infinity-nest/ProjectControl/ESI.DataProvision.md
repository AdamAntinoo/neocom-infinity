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
# Assets
curl -X GET "https://esi.evetech.net/latest/characters/93813310/assets/?datasource=tranquility&page=1" -H "accept: application/json" -H "authorization: Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6IkpXVC1TaWduYXR1cmUtS2V5IiwidHlwIjoiSldUIn0.eyJzY3AiOlsiZXNpLWFzc2V0cy5yZWFkX2Fzc2V0cy52MSIsImVzaS1hc3NldHMucmVhZF9jb3Jwb3JhdGlvbl9hc3NldHMudjEiXSwianRpIjoiMjFjOWNhZjYtODczNS00NzYwLThkNjAtYzFiYWRiMzE4MmNjIiwia2lkIjoiSldULVNpZ25hdHVyZS1LZXkiLCJzdWIiOiJDSEFSQUNURVI6RVZFOjkzODEzMzEwIiwiYXpwIjoiNjgzMDg0YWI1Zjg4NDhkNGIxODc0NjJhYzNiOTc2NzciLCJ0ZW5hbnQiOiJ0cmFucXVpbGl0eSIsInRpZXIiOiJsaXZlIiwicmVnaW9uIjoid29ybGQiLCJhdWQiOlsiNjgzMDg0YWI1Zjg4NDhkNGIxODc0NjJhYzNiOTc2NzciLCJFVkUgT25saW5lIl0sIm5hbWUiOiJQZXJpY28gVHVlcnRvIiwib3duZXIiOiJRc2lrT2pXUFFERnAzM1hucEl1VzhnM0Z5eFE9IiwiZXhwIjoxNzE2ODAzMzU4LCJpYXQiOjE3MTY4MDIxNTgsImlzcyI6Imh0dHBzOi8vbG9naW4uZXZlb25saW5lLmNvbSJ9.VD-juLaj3-b7vIMGSqCWrL52dMmaSbo3CE6rgFewS8QoDVEofStr2OR5-7N_5wLk2DXXRH6oioS_LBbd3LycuTg6-gNgqaqF0uZckL2l3Sm1lF_6eYs-FFNsXMr-Frjlh5hV17R5Zh_5LwbCNiBX2pMn31sPzJK5MfY94VgWXI5PdHOvCDh0zXm3xKtzU9tBrF82CKGGelAOgP1j_ttLaMSDJbTSTv4hhn5b7DLF-0eSF8hvAGQbJDEODX2MvlANpwPt6gX_5bTlm_nYdV6O7aoGzyB368GrCSUB8YKSvywiH-osKKk4c5fVO3q0kcrYQhFpBNeiNOAAmqK0hs1yBg" -H "Cache-Control: no-cache"
[
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012139393152,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1540
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015046088204,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1245
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015138739159,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11613
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012565456204,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2604
  },
  {
    "is_singleton": true,
    "item_id": 1035152469079,
    "location_flag": "MedSlot0",
    "location_id": 1035152469075,
    "location_type": "item",
    "quantity": 1,
    "type_id": 21857
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010224088482,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012654676570,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11613
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012749004465,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1446
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015138739144,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11613
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011703411737,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1196
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1034992525141,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 17332
  },
  {
    "is_singleton": false,
    "item_id": 1035310171012,
    "location_flag": "AutoFit",
    "location_id": 1035124094434,
    "location_type": "item",
    "quantity": 1,
    "type_id": 252
  },
  {
    "is_singleton": false,
    "item_id": 1035403127651,
    "location_flag": "Hangar",
    "location_id": 60004588,
    "location_type": "station",
    "quantity": 10,
    "type_id": 23097
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009696268051,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010281219740,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11302
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011988177040,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1245
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012694074160,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1404
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015138723497,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1245
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011772708070,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1196
  },
  {
    "is_singleton": false,
    "item_id": 1035176444286,
    "location_flag": "Hangar",
    "location_id": 60010699,
    "location_type": "station",
    "quantity": 11,
    "type_id": 1226
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012569515679,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 828
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015093622029,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11613
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015093622721,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1404
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011772708019,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11613
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012694074159,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1404
  },
  {
    "is_singleton": false,
    "item_id": 1035406276549,
    "location_flag": "Cargo",
    "location_id": 1035178817324,
    "location_type": "item",
    "quantity": 2,
    "type_id": 23079
  },
  {
    "is_singleton": false,
    "item_id": 1035393254515,
    "location_flag": "AutoFit",
    "location_id": 1035425458067,
    "location_type": "item",
    "quantity": 1,
    "type_id": 5320
  },
  {
    "is_singleton": false,
    "item_id": 1035410472235,
    "location_flag": "AutoFit",
    "location_id": 1035425458067,
    "location_type": "item",
    "quantity": 61,
    "type_id": 15625
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015046088148,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2604
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015093622034,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11613
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1017503541073,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11358
  },
  {
    "is_singleton": true,
    "item_id": 1034950844353,
    "location_flag": "AutoFit",
    "location_id": 1017096452525,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11268
  },
  {
    "is_singleton": true,
    "item_id": 1018064865993,
    "location_flag": "LoSlot3",
    "location_id": 1019123153786,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2605
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010395119514,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11306
  },
  {
    "is_singleton": true,
    "item_id": 1035013588778,
    "location_flag": "HiSlot3",
    "location_id": 1035013289119,
    "location_type": "item",
    "quantity": 1,
    "type_id": 561
  },
  {
    "is_singleton": false,
    "item_id": 1018575123991,
    "location_flag": "AutoFit",
    "location_id": 1035025574825,
    "location_type": "item",
    "quantity": 1,
    "type_id": 3454
  },
  {
    "is_singleton": true,
    "item_id": 1035136964283,
    "location_flag": "LoSlot0",
    "location_id": 1035103634311,
    "location_type": "item",
    "quantity": 1,
    "type_id": 22542
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009357548049,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11302
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012627069345,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 12275
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015138887688,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2604
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015217369376,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1245
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1017503541066,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11358
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010281219738,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11302
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012512986388,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1404
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015046121370,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1404
  },
  {
    "is_singleton": true,
    "item_id": 1035153812288,
    "location_flag": "DroneBay",
    "location_id": 1034962148671,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2203
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012283194816,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1404
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010281219754,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11302
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010306934341,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 60341
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012451147790,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11358
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015093622027,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11613
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015138723430,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2604
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1018110303607,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 820
  },
  {
    "is_singleton": false,
    "item_id": 1035387560565,
    "location_flag": "AutoFit",
    "location_id": 1034960394263,
    "location_type": "item",
    "quantity": 1,
    "type_id": 4787
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009550214900,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11306
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010627187737,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11304
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011772708025,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11613
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009732852236,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011961005899,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11304
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012139393166,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1540
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012654676555,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11613
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012770617696,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1404
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015138739142,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11613
  },
  {
    "is_singleton": true,
    "item_id": 1035153914173,
    "location_flag": "DroneBay",
    "location_id": 1034962148671,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2203
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012029708178,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1318
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012749004479,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1446
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1032766184869,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 836
  },
  {
    "is_singleton": false,
    "item_id": 1035433136745,
    "location_flag": "AutoFit",
    "location_id": 1035425458067,
    "location_type": "item",
    "quantity": 100,
    "type_id": 181
  },
  {
    "is_singleton": false,
    "item_id": 1044485725299,
    "location_flag": "Hangar",
    "location_id": 60010699,
    "location_type": "station",
    "quantity": 17306,
    "type_id": 17464
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009770070153,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015046088211,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1245
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015046121360,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1404
  },
  {
    "is_singleton": true,
    "item_id": 1034962148653,
    "location_flag": "MedSlot1",
    "location_id": 1034962148671,
    "location_type": "item",
    "quantity": 1,
    "type_id": 6073
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009550215694,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11302
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012050741213,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1318
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010224088474,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010306934339,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 60341
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012111048578,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1318
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012208988118,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1404
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012208988122,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1404
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012598795606,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 9945
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012770617682,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1404
  },
  {
    "is_singleton": true,
    "item_id": 1035136964296,
    "location_flag": "DroneBay",
    "location_id": 1035103634311,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2486
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010281219746,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11302
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010281219748,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11302
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012705384864,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 12275
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015217369349,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2604
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1017503540818,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1245
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1032766184727,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 835
  },
  {
    "is_singleton": true,
    "item_id": 1035103634311,
    "location_flag": "Hangar",
    "location_id": 60008680,
    "location_type": "station",
    "quantity": 1,
    "type_id": 32880
  },
  {
    "is_singleton": false,
    "item_id": 1035393254496,
    "location_flag": "Hangar",
    "location_id": 60010066,
    "location_type": "station",
    "quantity": 2,
    "type_id": 2048
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009633950202,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11302
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011772708033,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11613
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009770070147,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011861112807,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1540
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012588511569,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1404
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012694074174,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1404
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015138723495,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1245
  },
  {
    "is_singleton": true,
    "item_id": 1034962148647,
    "location_flag": "MedSlot0",
    "location_id": 1034962148671,
    "location_type": "item",
    "quantity": 1,
    "type_id": 35656
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1032783074403,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2204
  },
  {
    "is_singleton": false,
    "item_id": 1044628792442,
    "location_flag": "Hangar",
    "location_id": 60010699,
    "location_type": "station",
    "quantity": 5908,
    "type_id": 17459
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009770070299,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010224088476,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010306934345,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 60341
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011772708296,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 22543
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012208988124,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1404
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012524447958,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 9945
  },
  {
    "is_singleton": true,
    "item_id": 1035408704388,
    "location_flag": "HiSlot1",
    "location_id": 1035178817324,
    "location_type": "item",
    "quantity": 1,
    "type_id": 23071
  },
  {
    "is_singleton": true,
    "item_id": 1035421507825,
    "location_flag": "Hangar",
    "location_id": 60003760,
    "location_type": "station",
    "quantity": 1,
    "type_id": 561
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009633950210,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11302
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011988176919,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2047
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012569515661,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 828
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012111048546,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1318
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012694460800,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2604
  },
  {
    "is_singleton": true,
    "item_id": 1035406470634,
    "location_flag": "RigSlot1",
    "location_id": 1035178817324,
    "location_type": "item",
    "quantity": 1,
    "type_id": 26929
  },
  {
    "is_singleton": false,
    "item_id": 1035410718293,
    "location_flag": "AutoFit",
    "location_id": 1035425458067,
    "location_type": "item",
    "quantity": 1,
    "type_id": 8759
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015046088199,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1245
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011772708299,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 22543
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012139393220,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2332
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009770070196,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012654676564,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11613
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012694460791,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2604
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012770652258,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1245
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012770652329,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2604
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015138887572,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1404
  },
  {
    "is_singleton": true,
    "item_id": 1004406235971,
    "location_flag": "AutoFit",
    "location_id": 1017096452525,
    "location_type": "item",
    "quantity": 1,
    "type_id": 785
  },
  {
    "is_singleton": true,
    "item_id": 1035152763498,
    "location_flag": "LoSlot0",
    "location_id": 1023946861628,
    "location_type": "item",
    "quantity": 1,
    "type_id": 16391
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1008867024187,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1096
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012694460777,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2604
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015138723432,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2604
  },
  {
    "is_singleton": false,
    "item_id": 1012115480602,
    "location_flag": "Hangar",
    "location_id": 60015136,
    "location_type": "station",
    "quantity": 132,
    "type_id": 262
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011961005896,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11304
  },
  {
    "is_singleton": true,
    "item_id": 1018857900375,
    "location_flag": "AutoFit",
    "location_id": 1017096452525,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2445
  },
  {
    "is_singleton": true,
    "item_id": 1034962153673,
    "location_flag": "LoSlot3",
    "location_id": 1034962148671,
    "location_type": "item",
    "quantity": 1,
    "type_id": 9944
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015033991572,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2332
  },
  {
    "is_singleton": true,
    "item_id": 1035167253834,
    "location_flag": "Hangar",
    "location_id": 60008539,
    "location_type": "station",
    "quantity": 1,
    "type_id": 596
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010881151130,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1540
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012770652318,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2604
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011988176899,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2047
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012524447998,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1446
  },
  {
    "is_singleton": true,
    "item_id": 1018784205401,
    "location_flag": "Hangar",
    "location_id": 1036030944542,
    "location_type": "item",
    "quantity": 1,
    "type_id": 25895
  },
  {
    "is_singleton": true,
    "item_id": 1035104423347,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "location_type": "station",
    "quantity": 1,
    "type_id": 11489
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012629890798,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2604
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012694460843,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1245
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012770652260,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1245
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1017503540199,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1404
  },
  {
    "is_singleton": true,
    "item_id": 1035311853278,
    "location_flag": "HiSlot1",
    "location_id": 1035311853276,
    "location_type": "item",
    "quantity": 1,
    "type_id": 3651
  },
  {
    "is_singleton": false,
    "item_id": 1035159702797,
    "location_flag": "Hangar",
    "location_id": 60008500,
    "location_type": "station",
    "quantity": 12,
    "type_id": 2486
  },
  {
    "is_singleton": false,
    "item_id": 1015297959971,
    "location_flag": "Hangar",
    "location_id": 60015136,
    "location_type": "station",
    "quantity": 2,
    "type_id": 15630
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012569515665,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 828
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015093622754,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2604
  },
  {
    "is_singleton": true,
    "item_id": 1035406301180,
    "location_flag": "Hangar",
    "location_id": 60003760,
    "location_type": "station",
    "quantity": 1,
    "type_id": 1335
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009732852248,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012006529260,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 60341
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015093622784,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2604
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015217369356,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2604
  },
  {
    "is_singleton": true,
    "item_id": 1018046736248,
    "location_flag": "AutoFit",
    "location_id": 1017096452525,
    "location_type": "item",
    "quantity": 1,
    "type_id": 17334
  },
  {
    "is_singleton": true,
    "item_id": 1035406470628,
    "location_flag": "RigSlot0",
    "location_id": 1035178817324,
    "location_type": "item",
    "quantity": 1,
    "type_id": 31364
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011703411731,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1196
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011988177011,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1245
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012654676544,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11613
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012654676572,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11613
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012770652321,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2604
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1017503540828,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1245
  },
  {
    "is_singleton": true,
    "item_id": 1017309550593,
    "location_flag": "AutoFit",
    "location_id": 1017096452525,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2487
  },
  {
    "is_singleton": true,
    "item_id": 1019089882991,
    "location_flag": "MedSlot3",
    "location_id": 1019123153786,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2539
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012694074156,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1404
  },
  {
    "is_singleton": false,
    "item_id": 1045200958262,
    "location_flag": "Hangar",
    "location_id": 60008680,
    "location_type": "station",
    "quantity": 320,
    "type_id": 20
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010395119531,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11306
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1017503540201,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1404
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1017503540408,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2604
  },
  {
    "is_singleton": true,
    "item_id": 1018857902519,
    "location_flag": "AutoFit",
    "location_id": 1017096452525,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2477
  },
  {
    "is_singleton": false,
    "item_id": 1035168754338,
    "location_flag": "Cargo",
    "location_id": 1034962148671,
    "location_type": "item",
    "quantity": 400,
    "type_id": 230
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009624924648,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_singleton": true,
    "item_id": 1035406298339,
    "location_flag": "Hangar",
    "location_id": 60003760,
    "location_type": "station",
    "quantity": 1,
    "type_id": 8517
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1008867024237,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1096
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009770069958,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012512986520,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2294
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015046121358,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1404
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015093622756,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2604
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015138887672,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2604
  },
  {
    "is_singleton": true,
    "item_id": 1009780033706,
    "location_flag": "AutoFit",
    "location_id": 1019312782218,
    "location_type": "item",
    "quantity": 1,
    "type_id": 31743
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009550215700,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11302
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015046121335,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1404
  },
  {
    "is_singleton": true,
    "item_id": 1018784203335,
    "location_flag": "AutoFit",
    "location_id": 1019312782218,
    "location_type": "item",
    "quantity": 1,
    "type_id": 25737
  },
  {
    "is_singleton": true,
    "item_id": 1009580504065,
    "location_flag": "AutoFit",
    "location_id": 1034932618957,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11358
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012029708174,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1318
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012283194899,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2604
  },
  {
    "is_singleton": false,
    "item_id": 1035425597136,
    "location_flag": "AutoFit",
    "location_id": 1035425458067,
    "location_type": "item",
    "quantity": 1,
    "type_id": 4529
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012770652266,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1245
  },
  {
    "is_singleton": true,
    "item_id": 1015634217499,
    "location_flag": "AutoFit",
    "location_id": 1017096452525,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1354
  },
  {
    "is_singleton": true,
    "item_id": 1034891302340,
    "location_flag": "HiSlot4",
    "location_id": 1035013289119,
    "location_type": "item",
    "quantity": 1,
    "type_id": 561
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1008884857664,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1096
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010224088502,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012749004459,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1446
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015046061540,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11613
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015138723444,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2604
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015217369333,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2604
  },
  {
    "is_singleton": true,
    "item_id": 1017783038530,
    "location_flag": "HiSlot1",
    "location_id": 1017783038524,
    "location_type": "item",
    "quantity": 1,
    "type_id": 3651
  },
  {
    "is_singleton": false,
    "item_id": 1035387537783,
    "location_flag": "AutoFit",
    "location_id": 1034960394263,
    "location_type": "item",
    "quantity": 1,
    "type_id": 240
  },
  {
    "is_singleton": true,
    "item_id": 1035491153579,
    "location_flag": "MedSlot0",
    "location_id": 1035491153575,
    "location_type": "item",
    "quantity": 1,
    "type_id": 21857
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1008867024235,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1096
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010224088345,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 60341
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011464995812,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1196
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015046121348,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1404
  },
  {
    "is_singleton": true,
    "item_id": 1034962161952,
    "location_flag": "HiSlot2",
    "location_id": 1034962148671,
    "location_type": "item",
    "quantity": 1,
    "type_id": 14278
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009550215698,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11302
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009730695137,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009933398033,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 60341
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012211345745,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1404
  },
  {
    "is_singleton": false,
    "item_id": 1044629018502,
    "location_flag": "Hangar",
    "location_id": 60010699,
    "location_type": "station",
    "quantity": 756,
    "type_id": 1226
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1008867024242,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1096
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011861112796,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1540
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012006529258,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 60341
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015138723482,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1245
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1032766184730,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 835
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012524445311,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 828
  },
  {
    "is_singleton": true,
    "item_id": 1035136964284,
    "location_flag": "MedSlot1",
    "location_id": 1035103634311,
    "location_type": "item",
    "quantity": 1,
    "type_id": 6569
  },
  {
    "is_singleton": false,
    "item_id": 1035392964537,
    "location_flag": "AutoFit",
    "location_id": 1035425458067,
    "location_type": "item",
    "quantity": 1,
    "type_id": 29007
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010306934336,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 60341
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012569515674,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 828
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012770617693,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1404
  },
  {
    "is_singleton": true,
    "item_id": 1011723766731,
    "location_flag": "AutoFit",
    "location_id": 1017096452525,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2292
  },
  {
    "is_singleton": true,
    "item_id": 1017368236037,
    "location_flag": "AutoFit",
    "location_id": 1017096452525,
    "location_type": "item",
    "quantity": 1,
    "type_id": 15511
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009550214903,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11306
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010627187742,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11304
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011772708030,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11613
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012694074154,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1404
  },
  {
    "is_singleton": false,
    "item_id": 1012288465998,
    "location_flag": "Hangar",
    "location_id": 60015136,
    "location_type": "station",
    "quantity": 99,
    "type_id": 251
  },
  {
    "is_singleton": false,
    "item_id": 1035176913554,
    "location_flag": "Hangar",
    "location_id": 60015136,
    "location_type": "station",
    "quantity": 1,
    "type_id": 17261
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012770652381,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1318
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009770070501,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011772708091,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1196
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012598795592,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 9945
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1034992525135,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 17332
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1035034817082,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2445
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012139393210,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2332
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012770617699,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1404
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012770652326,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2604
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1017503541071,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11358
  },
  {
    "is_singleton": true,
    "item_id": 1009144613825,
    "location_flag": "AutoFit",
    "location_id": 1017096452525,
    "location_type": "item",
    "quantity": 1,
    "type_id": 60341
  },
  {
    "is_singleton": true,
    "item_id": 1011792982617,
    "location_flag": "AutoFit",
    "location_id": 1017096452525,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1137
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011772708044,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11613
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1034992525144,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 17332
  },
  {
    "is_singleton": true,
    "item_id": 1023669902378,
    "location_flag": "AutoFit",
    "location_id": 1035124094434,
    "location_type": "item",
    "quantity": 1,
    "type_id": 23081
  },
  {
    "is_singleton": false,
    "item_id": 1035013915320,
    "location_flag": "AutoFit",
    "location_id": 1035124094434,
    "location_type": "item",
    "quantity": 500,
    "type_id": 22963
  },
  {
    "is_singleton": false,
    "item_id": 1035159705137,
    "location_flag": "AutoFit",
    "location_id": 1035124094434,
    "location_type": "item",
    "quantity": 1,
    "type_id": 5839
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015138739154,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11613
  },
  {
    "is_singleton": true,
    "item_id": 1008266912283,
    "location_flag": "AutoFit",
    "location_id": 1034932618957,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1318
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009412208060,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11302
  },
  {
    "is_singleton": true,
    "item_id": 1035131521818,
    "location_flag": "MedSlot2",
    "location_id": 1035013289119,
    "location_type": "item",
    "quantity": 1,
    "type_id": 6173
  },
  {
    "is_singleton": false,
    "item_id": 1018575130202,
    "location_flag": "AutoFit",
    "location_id": 1035025574825,
    "location_type": "item",
    "quantity": 1,
    "type_id": 3436
  },
  {
    "is_singleton": true,
    "item_id": 1019312782218,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "location_type": "station",
    "quantity": 1,
    "type_id": 3467
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012524445348,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11358
  },
  {
    "is_singleton": true,
    "item_id": 1031896390494,
    "location_flag": "RigSlot2",
    "location_id": 1031896390464,
    "location_type": "item",
    "quantity": 1,
    "type_id": 31370
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011816287970,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1129
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012524445346,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11358
  },
  {
    "is_singleton": false,
    "item_id": 1035433120043,
    "location_flag": "AutoFit",
    "location_id": 1035425458067,
    "location_type": "item",
    "quantity": 1,
    "type_id": 8089
  },
  {
    "is_singleton": false,
    "item_id": 1035176404395,
    "location_flag": "Hangar",
    "location_id": 60010699,
    "location_type": "station",
    "quantity": 514,
    "type_id": 17448
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010281219743,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11302
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012283194821,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2294
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012770652281,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1245
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015138723502,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1245
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015138887718,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11358
  },
  {
    "is_singleton": true,
    "item_id": 1035152556467,
    "location_flag": "Cargo",
    "location_id": 1023946861628,
    "location_type": "item",
    "quantity": 1,
    "type_id": 23071
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009550214894,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11306
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1035034817139,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 886
  },
  {
    "is_singleton": true,
    "item_id": 1017217371294,
    "location_flag": "AutoFit",
    "location_id": 1035124094434,
    "location_type": "item",
    "quantity": 1,
    "type_id": 23099
  },
  {
    "is_singleton": true,
    "item_id": 1035406405264,
    "location_flag": "Hangar",
    "location_id": 60003760,
    "location_type": "station",
    "quantity": 1,
    "type_id": 1405
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009483062353,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 24349
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009732838977,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012111048575,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1318
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012673323776,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 3840
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012770617705,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1404
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015138887635,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1404
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1017503540831,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1245
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011772708042,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11613
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012627069350,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 12275
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015217369381,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1245
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1034992525138,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 17332
  },
  {
    "is_singleton": false,
    "item_id": 1035310392736,
    "location_flag": "Hangar",
    "location_id": 60008500,
    "location_type": "station",
    "quantity": 1,
    "type_id": 33475
  },
  {
    "is_singleton": false,
    "item_id": 1045200891863,
    "location_flag": "Hangar",
    "location_id": 60008680,
    "location_type": "station",
    "quantity": 792,
    "type_id": 1224
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1008867024244,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1096
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010281219751,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11302
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011816288006,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11613
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012569515659,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 828
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015093622041,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11613
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1017503540823,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1245
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011861112794,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1540
  },
  {
    "is_singleton": false,
    "item_id": 1035392964531,
    "location_flag": "AutoFit",
    "location_id": 1035425458067,
    "location_type": "item",
    "quantity": 1,
    "type_id": 29005
  },
  {
    "is_singleton": false,
    "item_id": 1035425392442,
    "location_flag": "AutoFit",
    "location_id": 1035425458067,
    "location_type": "item",
    "quantity": 1,
    "type_id": 5279
  },
  {
    "is_singleton": true,
    "item_id": 1034962800851,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "location_type": "station",
    "quantity": 1,
    "type_id": 3467
  },
  {
    "is_singleton": false,
    "item_id": 1035406213546,
    "location_flag": "Hangar",
    "location_id": 60015136,
    "location_type": "station",
    "quantity": 9,
    "type_id": 11297
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010224088479,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012139393218,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2332
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012451147691,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2604
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012512986413,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1404
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015217369371,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1245
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1035034817224,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 17334
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010395119527,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11306
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011861112804,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1540
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012694074165,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1404
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012694074169,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1404
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009412208066,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11302
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012082100596,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2292
  },
  {
    "is_singleton": false,
    "item_id": 1018899517189,
    "location_flag": "AutoFit",
    "location_id": 1035025574825,
    "location_type": "item",
    "quantity": 2,
    "type_id": 3420
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009770070301,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012006529273,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 60341
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015046088216,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1245
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1032766184872,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 836
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012749004470,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1446
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015033991565,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2332
  },
  {
    "is_singleton": false,
    "item_id": 1035410846830,
    "location_flag": "AutoFit",
    "location_id": 1035425458067,
    "location_type": "item",
    "quantity": 100,
    "type_id": 212
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012139393155,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1540
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012565456193,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2604
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015138739156,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11613
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015138887683,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2604
  },
  {
    "is_singleton": true,
    "item_id": 1018857903307,
    "location_flag": "AutoFit",
    "location_id": 1017096452525,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2194
  },
  {
    "is_singleton": true,
    "item_id": 1020408990737,
    "location_flag": "AutoFit",
    "location_id": 1017096452525,
    "location_type": "item",
    "quantity": 1,
    "type_id": 847
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009412208054,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11302
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012211345736,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1404
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1017503540399,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2604
  },
  {
    "is_singleton": true,
    "item_id": 1009000185979,
    "location_flag": "AutoFit",
    "location_id": 1017096452525,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11302
  },
  {
    "is_singleton": true,
    "item_id": 1011403938011,
    "location_flag": "AutoFit",
    "location_id": 1017096452525,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11873
  },
  {
    "is_singleton": true,
    "item_id": 1018792407330,
    "location_flag": "AutoFit",
    "location_id": 1017096452525,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1876
  },
  {
    "is_singleton": true,
    "item_id": 1035152556862,
    "location_flag": "Cargo",
    "location_id": 1023946861628,
    "location_type": "item",
    "quantity": 1,
    "type_id": 23079
  },
  {
    "is_singleton": true,
    "item_id": 1035408704387,
    "location_flag": "HiSlot0",
    "location_id": 1035178817324,
    "location_type": "item",
    "quantity": 1,
    "type_id": 23071
  },
  {
    "is_singleton": true,
    "item_id": 1035311801364,
    "location_flag": "Hangar",
    "location_id": 60008500,
    "location_type": "station",
    "quantity": 1,
    "type_id": 33475
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009696268048,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011464995855,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11306
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011861112802,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1540
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011988177043,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1245
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012139393149,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1540
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012694074163,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1404
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015046121363,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1404
  },
  {
    "is_singleton": true,
    "item_id": 1012284182091,
    "location_flag": "AutoFit",
    "location_id": 1017096452525,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1446
  },
  {
    "is_singleton": true,
    "item_id": 1024137517699,
    "location_flag": "AutoFit",
    "location_id": 1017096452525,
    "location_type": "item",
    "quantity": 1,
    "type_id": 840
  },
  {
    "is_singleton": true,
    "item_id": 1034962148650,
    "location_flag": "RigSlot0",
    "location_id": 1034962148671,
    "location_type": "item",
    "quantity": 1,
    "type_id": 31372
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012050741211,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1318
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012082100610,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2292
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012142143555,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11278
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012770617701,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1404
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010306934328,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 60341
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011772708038,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11613
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1032766184866,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 836
  },
  {
    "is_singleton": false,
    "item_id": 1035410491673,
    "location_flag": "AutoFit",
    "location_id": 1035425458067,
    "location_type": "item",
    "quantity": 2,
    "type_id": 5319
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011464995859,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11306
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015138723437,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2604
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015138887675,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2604
  },
  {
    "is_singleton": true,
    "item_id": 1018861867524,
    "location_flag": "AutoFit",
    "location_id": 1017096452525,
    "location_type": "item",
    "quantity": 1,
    "type_id": 23560
  },
  {
    "is_singleton": true,
    "item_id": 1007955093774,
    "location_flag": "AutoFit",
    "location_id": 1019312782218,
    "location_type": "item",
    "quantity": 1,
    "type_id": 31717
  },
  {
    "is_singleton": false,
    "item_id": 1044485829146,
    "location_flag": "Hangar",
    "location_id": 60010699,
    "location_type": "station",
    "quantity": 2090,
    "type_id": 17459
  },
  {
    "is_singleton": false,
    "item_id": 1015089135233,
    "location_flag": "Hangar",
    "location_id": 60015136,
    "location_type": "station",
    "quantity": 2,
    "type_id": 15629
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012139393147,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1540
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012770652263,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1245
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015138723447,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2604
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015217369338,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2604
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015217369389,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1245
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1032783074400,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2204
  },
  {
    "is_singleton": true,
    "item_id": 1008664394509,
    "location_flag": "AutoFit",
    "location_id": 1017096452525,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1096
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1035017913320,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 17334
  },
  {
    "is_singleton": true,
    "item_id": 1035311853277,
    "location_flag": "HiSlot0",
    "location_id": 1035311853276,
    "location_type": "item",
    "quantity": 1,
    "type_id": 3634
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011772708073,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1196
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015217369368,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1245
  },
  {
    "is_singleton": false,
    "item_id": 1035311682965,
    "location_flag": "Hangar",
    "location_id": 60008500,
    "location_type": "station",
    "quantity": 8000,
    "type_id": 212
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012006529249,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 60341
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011988176916,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2047
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012142143498,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11278
  },
  {
    "is_singleton": true,
    "item_id": 1035417307374,
    "location_flag": "Cargo",
    "location_id": 1035178817324,
    "location_type": "item",
    "quantity": 1,
    "type_id": 23083
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012451147700,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2604
  },
  {
    "is_singleton": true,
    "item_id": 1012030853635,
    "location_flag": "AutoFit",
    "location_id": 1017096452525,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11891
  },
  {
    "is_singleton": true,
    "item_id": 1022327065476,
    "location_flag": "AutoFit",
    "location_id": 1034932618957,
    "location_type": "item",
    "quantity": 1,
    "type_id": 31359
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1008867024224,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1096
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012451147677,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2604
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012629890787,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2604
  },
  {
    "is_singleton": true,
    "item_id": 1009124016259,
    "location_flag": "AutoFit",
    "location_id": 1034932618957,
    "location_type": "item",
    "quantity": 1,
    "type_id": 24349
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009483062167,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012451189132,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11358
  },
  {
    "is_singleton": false,
    "item_id": 1035160176955,
    "location_flag": "Hangar",
    "location_id": 60015136,
    "location_type": "station",
    "quantity": 2,
    "type_id": 15614
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010881151135,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1540
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011988177008,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1245
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011988177025,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1245
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012451147688,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2604
  },
  {
    "is_singleton": true,
    "item_id": 1035124094434,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "location_type": "station",
    "quantity": 1,
    "type_id": 11489
  },
  {
    "is_singleton": false,
    "item_id": 1035444663794,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "location_type": "station",
    "quantity": 1,
    "type_id": 596
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012524445548,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 12275
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012524448012,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1446
  },
  {
    "is_singleton": true,
    "item_id": 1018858029040,
    "location_flag": "AutoFit",
    "location_id": 1017096452525,
    "location_type": "item",
    "quantity": 1,
    "type_id": 17325
  },
  {
    "is_singleton": true,
    "item_id": 1019030614679,
    "location_flag": "HiSlot0",
    "location_id": 1019123153786,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11577
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010627187716,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11304
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011464995861,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11306
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011464995878,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11306
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012512986442,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11358
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012770617691,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1404
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015093622726,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1404
  },
  {
    "is_singleton": true,
    "item_id": 1035136964289,
    "location_flag": "HiSlot1",
    "location_id": 1035103634311,
    "location_type": "item",
    "quantity": 1,
    "type_id": 5245
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015093622761,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2604
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015138887677,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2604
  },
  {
    "is_singleton": true,
    "item_id": 1018663911924,
    "location_flag": "AutoFit",
    "location_id": 1017096452525,
    "location_type": "item",
    "quantity": 1,
    "type_id": 4394
  },
  {
    "is_singleton": false,
    "item_id": 1035387563595,
    "location_flag": "AutoFit",
    "location_id": 1034960394263,
    "location_type": "item",
    "quantity": 1,
    "type_id": 15331
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012142143518,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11278
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012211345754,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1404
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012029708246,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2292
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1017503540196,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1404
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1008884857671,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1096
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1035034817136,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 886
  },
  {
    "is_singleton": false,
    "item_id": 1035417552481,
    "location_flag": "AutoFit",
    "location_id": 1035425458067,
    "location_type": "item",
    "quantity": 1,
    "type_id": 4025
  },
  {
    "is_singleton": false,
    "item_id": 1035304891552,
    "location_flag": "Hangar",
    "location_id": 60010066,
    "location_type": "station",
    "quantity": 116,
    "type_id": 28668
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015046061522,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11613
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015046088141,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2604
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1017503540403,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2604
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009633950215,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11302
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015046061530,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11613
  },
  {
    "is_singleton": true,
    "item_id": 1011792983394,
    "location_flag": "AutoFit",
    "location_id": 1017096452525,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1161
  },
  {
    "is_singleton": true,
    "item_id": 1034962151905,
    "location_flag": "MedSlot2",
    "location_id": 1034962148671,
    "location_type": "item",
    "quantity": 1,
    "type_id": 4435
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1017503540191,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1404
  },
  {
    "is_singleton": false,
    "item_id": 1035126503511,
    "location_flag": "AutoFit",
    "location_id": 1035124094434,
    "location_type": "item",
    "quantity": 8,
    "type_id": 2486
  },
  {
    "is_singleton": true,
    "item_id": 1035406470633,
    "location_flag": "LoSlot0",
    "location_id": 1035178817324,
    "location_type": "item",
    "quantity": 1,
    "type_id": 5839
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009696268044,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015046121353,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1404
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1017503541068,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11358
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009357548094,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11278
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009550215703,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11302
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009696268042,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010220961974,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 60341
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012283194839,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2294
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1032783074216,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2487
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009732852123,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012305537000,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11358
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015217369328,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2604
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1017503540411,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2604
  },
  {
    "is_singleton": true,
    "item_id": 1008999830819,
    "location_flag": "AutoFit",
    "location_id": 1017096452525,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1129
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009624924647,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012524445266,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 828
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1032783073796,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2184
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009770070181,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015046088116,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2604
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015046088139,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2604
  },
  {
    "is_singleton": false,
    "item_id": 1035434125162,
    "location_flag": "AutoFit",
    "location_id": 1035425458067,
    "location_type": "item",
    "quantity": 100,
    "type_id": 2516
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010224088347,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 60341
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012524448010,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1446
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012627069351,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 12275
  },
  {
    "is_singleton": false,
    "item_id": 1019131552105,
    "location_flag": "AutoFit",
    "location_id": 1035025574825,
    "location_type": "item",
    "quantity": 2,
    "type_id": 26254
  },
  {
    "is_singleton": false,
    "item_id": 1035410491672,
    "location_flag": "AutoFit",
    "location_id": 1035425458067,
    "location_type": "item",
    "quantity": 3,
    "type_id": 6485
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010281219742,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11302
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012111048597,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1318
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015138887631,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1404
  },
  {
    "is_singleton": true,
    "item_id": 1035136964287,
    "location_flag": "HiSlot0",
    "location_id": 1035103634311,
    "location_type": "item",
    "quantity": 1,
    "type_id": 5245
  },
  {
    "is_singleton": true,
    "item_id": 1036030944542,
    "location_flag": "AssetSafety",
    "location_id": 60006907,
    "location_type": "station",
    "quantity": 1,
    "type_id": 60
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012111048588,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1318
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012512986386,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1404
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012770617708,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1404
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012770652267,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1245
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015138739150,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11613
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015138887638,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1404
  },
  {
    "is_singleton": true,
    "item_id": 1018858033317,
    "location_flag": "AutoFit",
    "location_id": 1017096452525,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1153
  },
  {
    "is_singleton": true,
    "item_id": 1018861867511,
    "location_flag": "AutoFit",
    "location_id": 1017096452525,
    "location_type": "item",
    "quantity": 1,
    "type_id": 23564
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1034992525143,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 17332
  },
  {
    "is_singleton": false,
    "item_id": 1012129209947,
    "location_flag": "Hangar",
    "location_id": 60015136,
    "location_type": "station",
    "quantity": 120,
    "type_id": 260
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012139393154,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1540
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012629890790,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2604
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012705384862,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 12275
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015138739157,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11613
  },
  {
    "is_singleton": true,
    "item_id": 1012598006312,
    "location_flag": "AutoFit",
    "location_id": 1019312782218,
    "location_type": "item",
    "quantity": 1,
    "type_id": 31108
  },
  {
    "is_singleton": true,
    "item_id": 1035406268227,
    "location_flag": "LoSlot3",
    "location_id": 1035178817324,
    "location_type": "item",
    "quantity": 1,
    "type_id": 5849
  },
  {
    "is_singleton": true,
    "item_id": 1035072531054,
    "location_flag": "Hangar",
    "location_id": 1036030944542,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1112
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010220962030,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 60341
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1017503540820,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1245
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1017503540830,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1245
  },
  {
    "is_singleton": true,
    "item_id": 1035339617654,
    "location_flag": "DroneBay",
    "location_id": 1023946861628,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2203
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1035034817135,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 886
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009633950198,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11302
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1017503540412,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2604
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009412208051,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11302
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1035034817138,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 886
  },
  {
    "is_singleton": true,
    "item_id": 1035152469077,
    "location_flag": "HiSlot0",
    "location_id": 1035152469075,
    "location_type": "item",
    "quantity": 1,
    "type_id": 3634
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009770070306,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012111048586,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1318
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015093622025,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11613
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015138739140,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11613
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011772708041,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11613
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015217369382,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1245
  },
  {
    "is_singleton": false,
    "item_id": 1012035120310,
    "location_flag": "Hangar",
    "location_id": 60015136,
    "location_type": "station",
    "quantity": 31,
    "type_id": 240
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012166363421,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1245
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012524447962,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 9945
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015138723485,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1245
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015138739155,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11613
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015217369357,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2604
  },
  {
    "is_singleton": true,
    "item_id": 1009000339569,
    "location_flag": "AutoFit",
    "location_id": 1017096452525,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1196
  },
  {
    "is_singleton": false,
    "item_id": 1034962223811,
    "location_flag": "Cargo",
    "location_id": 1034962148671,
    "location_type": "item",
    "quantity": 760,
    "type_id": 226
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010306934349,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 60341
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012139393217,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2332
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012142143560,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11278
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011816288009,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11613
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012111048595,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1318
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012512986449,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11358
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015046088209,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1245
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1017503541075,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11358
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010220961967,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 60341
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011861112793,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1540
  },
  {
    "is_singleton": true,
    "item_id": 1017096452525,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "location_type": "station",
    "quantity": 1,
    "type_id": 3467
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009730695116,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_singleton": true,
    "item_id": 1010315913512,
    "location_flag": "AutoFit",
    "location_id": 1017096452525,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1540
  },
  {
    "is_singleton": true,
    "item_id": 1034960394263,
    "location_flag": "Cargo",
    "location_id": 1034962148671,
    "location_type": "item",
    "quantity": 1,
    "type_id": 3467
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010224088305,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 60343
  },
  {
    "is_singleton": true,
    "item_id": 1035159666774,
    "location_flag": "HiSlot4",
    "location_id": 1035122390165,
    "location_type": "item",
    "quantity": 1,
    "type_id": 24348
  },
  {
    "is_singleton": false,
    "item_id": 1035421868363,
    "location_flag": "Hangar",
    "location_id": 60003760,
    "location_type": "station",
    "quantity": 1,
    "type_id": 4027
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010224088470,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012694074166,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1404
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015093622776,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2604
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009412208065,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11302
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012512986508,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2294
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012598795610,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 9945
  },
  {
    "is_singleton": false,
    "item_id": 1012288489481,
    "location_flag": "Hangar",
    "location_id": 60015136,
    "location_type": "station",
    "quantity": 109,
    "type_id": 255
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015093622716,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1404
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015138723475,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1245
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015138723477,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1245
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015217369347,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2604
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1035520932762,
    "location_flag": "Hangar",
    "location_id": 1036030944542,
    "location_type": "item",
    "quantity": 1,
    "type_id": 31359
  },
  {
    "is_singleton": true,
    "item_id": 1012032017248,
    "location_flag": "AutoFit",
    "location_id": 1019312782218,
    "location_type": "item",
    "quantity": 1,
    "type_id": 31373
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010395119510,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11306
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011772708075,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1196
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012749004471,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1446
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1032766184867,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 836
  },
  {
    "is_singleton": false,
    "item_id": 1015881114516,
    "location_flag": "AutoFit",
    "location_id": 1035025574825,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11453
  },
  {
    "is_singleton": false,
    "item_id": 1045172302717,
    "location_flag": "Hangar",
    "location_id": 60010699,
    "location_type": "station",
    "quantity": 1,
    "type_id": 15331
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009633950204,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11302
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012565456194,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2604
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012565456196,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2604
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015138887686,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2604
  },
  {
    "is_singleton": true,
    "item_id": 1024031873884,
    "location_flag": "AutoFit",
    "location_id": 1017096452525,
    "location_type": "item",
    "quantity": 1,
    "type_id": 835
  },
  {
    "is_singleton": true,
    "item_id": 1035152554169,
    "location_flag": "Cargo",
    "location_id": 1023946861628,
    "location_type": "item",
    "quantity": 1,
    "type_id": 23083
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012166363429,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1245
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012283194824,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2294
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012588511575,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1404
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1032783074401,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2204
  },
  {
    "is_singleton": false,
    "item_id": 1035410675724,
    "location_flag": "Hangar",
    "location_id": 60010066,
    "location_type": "station",
    "quantity": 1,
    "type_id": 5973
  },
  {
    "is_singleton": false,
    "item_id": 1035159792727,
    "location_flag": "Hangar",
    "location_id": 60015136,
    "location_type": "station",
    "quantity": 1,
    "type_id": 15994
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010224088478,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010395119524,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11306
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012208988126,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1404
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012770617702,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1404
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1017503540398,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2604
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010306934335,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 60341
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012524445263,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 828
  },
  {
    "is_singleton": false,
    "item_id": 1012076844184,
    "location_flag": "AutoFit",
    "location_id": 1035025574825,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11529
  },
  {
    "is_singleton": false,
    "item_id": 1035167744624,
    "location_flag": "Hangar",
    "location_id": 60008500,
    "location_type": "station",
    "quantity": 12,
    "type_id": 2203
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010281219750,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11302
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011861112801,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1540
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012139393160,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1540
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012694074172,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1404
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015093622774,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2604
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1032766184725,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 835
  },
  {
    "is_singleton": true,
    "item_id": 1009124260755,
    "location_flag": "AutoFit",
    "location_id": 1017096452525,
    "location_type": "item",
    "quantity": 1,
    "type_id": 60341
  },
  {
    "is_singleton": false,
    "item_id": 1035410830446,
    "location_flag": "AutoFit",
    "location_id": 1035425458067,
    "location_type": "item",
    "quantity": 48,
    "type_id": 15628
  },
  {
    "is_singleton": false,
    "item_id": 1044628791505,
    "location_flag": "Hangar",
    "location_id": 60010699,
    "location_type": "station",
    "quantity": 882,
    "type_id": 17449
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009730695139,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009732852242,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011464995875,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11306
  },
  {
    "is_singleton": true,
    "item_id": 1008999826427,
    "location_flag": "AutoFit",
    "location_id": 1017096452525,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11616
  },
  {
    "is_singleton": true,
    "item_id": 1019495211620,
    "location_flag": "AutoFit",
    "location_id": 1019312782218,
    "location_type": "item",
    "quantity": 1,
    "type_id": 31036
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010224088335,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 60341
  },
  {
    "is_singleton": true,
    "item_id": 1035167253838,
    "location_flag": "MedSlot0",
    "location_id": 1035167253834,
    "location_type": "item",
    "quantity": 1,
    "type_id": 21857
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009770070504,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011703411733,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1196
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012139393163,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1540
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012654676558,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11613
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015093622044,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11613
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012050741210,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1318
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012524445267,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 828
  },
  {
    "is_singleton": true,
    "item_id": 1035406277539,
    "location_flag": "Cargo",
    "location_id": 1035178817324,
    "location_type": "item",
    "quantity": 1,
    "type_id": 23075
  },
  {
    "is_singleton": false,
    "item_id": 1035404421374,
    "location_flag": "Hangar",
    "location_id": 60004588,
    "location_type": "station",
    "quantity": 20,
    "type_id": 23115
  },
  {
    "is_singleton": false,
    "item_id": 1007330138963,
    "location_flag": "Hangar",
    "location_id": 60015136,
    "location_type": "station",
    "quantity": 7,
    "type_id": 15997
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012512986445,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11358
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1032783074217,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2487
  },
  {
    "is_singleton": true,
    "item_id": 1020036697973,
    "location_flag": "AutoFit",
    "location_id": 1017096452525,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11621
  },
  {
    "is_singleton": true,
    "item_id": 1012579169651,
    "location_flag": "AutoFit",
    "location_id": 1019312782218,
    "location_type": "item",
    "quantity": 1,
    "type_id": 25949
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1035017913328,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 17334
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012524447936,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 9945
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015138887578,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1404
  },
  {
    "is_singleton": true,
    "item_id": 1031896390471,
    "location_flag": "RigSlot1",
    "location_id": 1031896390464,
    "location_type": "item",
    "quantity": 1,
    "type_id": 31370
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009412208154,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11278
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1035017913319,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 17334
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011988177024,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1245
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012451147683,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2604
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015046088195,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1245
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015093622714,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1404
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1018110303608,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 820
  },
  {
    "is_singleton": false,
    "item_id": 1018899515815,
    "location_flag": "AutoFit",
    "location_id": 1035025574825,
    "location_type": "item",
    "quantity": 2,
    "type_id": 3439
  },
  {
    "is_singleton": true,
    "item_id": 1035311853276,
    "location_flag": "Hangar",
    "location_id": 60008500,
    "location_type": "station",
    "quantity": 1,
    "type_id": 596
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010881151132,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1540
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012694460789,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2604
  },
  {
    "is_singleton": true,
    "item_id": 1034950847758,
    "location_flag": "AutoFit",
    "location_id": 1017096452525,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11258
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011988176913,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2047
  },
  {
    "is_singleton": false,
    "item_id": 1018575175695,
    "location_flag": "AutoFit",
    "location_id": 1035025574825,
    "location_type": "item",
    "quantity": 2,
    "type_id": 26261
  },
  {
    "is_singleton": true,
    "item_id": 1035159664950,
    "location_flag": "HiSlot0",
    "location_id": 1035122390165,
    "location_type": "item",
    "quantity": 1,
    "type_id": 25861
  },
  {
    "is_singleton": true,
    "item_id": 1035417307371,
    "location_flag": "Cargo",
    "location_id": 1035178817324,
    "location_type": "item",
    "quantity": 1,
    "type_id": 23083
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012569515671,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 828
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012654676562,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11613
  },
  {
    "is_singleton": true,
    "item_id": 1034962148667,
    "location_flag": "MedSlot3",
    "location_id": 1034962148671,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1977
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011772708084,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1196
  },
  {
    "is_singleton": true,
    "item_id": 1035013289119,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "location_type": "station",
    "quantity": 1,
    "type_id": 32872
  },
  {
    "is_singleton": true,
    "item_id": 1035025574825,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "location_type": "station",
    "quantity": 1,
    "type_id": 3467
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010224088341,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 60341
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015138723500,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1245
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1017503540193,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1404
  },
  {
    "is_singleton": true,
    "item_id": 1008999803278,
    "location_flag": "AutoFit",
    "location_id": 1017096452525,
    "location_type": "item",
    "quantity": 1,
    "type_id": 828
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012569515657,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 828
  },
  {
    "is_singleton": true,
    "item_id": 1035131507952,
    "location_flag": "HiSlot5",
    "location_id": 1035013289119,
    "location_type": "item",
    "quantity": 1,
    "type_id": 5141
  },
  {
    "is_singleton": true,
    "item_id": 1022343115401,
    "location_flag": "AutoFit",
    "location_id": 1017096452525,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1178
  },
  {
    "is_singleton": true,
    "item_id": 1024031875056,
    "location_flag": "AutoFit",
    "location_id": 1017096452525,
    "location_type": "item",
    "quantity": 1,
    "type_id": 836
  },
  {
    "is_singleton": true,
    "item_id": 1023497880551,
    "location_flag": "AutoFit",
    "location_id": 1019312782218,
    "location_type": "item",
    "quantity": 1,
    "type_id": 31789
  },
  {
    "is_singleton": true,
    "item_id": 1035152763712,
    "location_flag": "LoSlot1",
    "location_id": 1023946861628,
    "location_type": "item",
    "quantity": 1,
    "type_id": 16391
  },
  {
    "is_singleton": false,
    "item_id": 1035153931515,
    "location_flag": "Cargo",
    "location_id": 1034962148671,
    "location_type": "item",
    "quantity": 100,
    "type_id": 229
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009412208156,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11278
  },
  {
    "is_singleton": false,
    "item_id": 1012171537421,
    "location_flag": "Hangar",
    "location_id": 60015136,
    "location_type": "station",
    "quantity": 102,
    "type_id": 259
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011464995877,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11306
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012111048548,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1318
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015046061523,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11613
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015093622762,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2604
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011772708027,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11613
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015138887678,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2604
  },
  {
    "is_singleton": true,
    "item_id": 1035425458067,
    "location_flag": "Hangar",
    "location_id": 60010066,
    "location_type": "station",
    "quantity": 1,
    "type_id": 11489
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1008884857659,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1096
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1017954058980,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 820
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009770070498,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1032783073797,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2184
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009730695128,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011464995860,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11306
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011464995864,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11306
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012569515677,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 828
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015046061538,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11613
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015093622727,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1404
  },
  {
    "is_singleton": false,
    "item_id": 1035387558069,
    "location_flag": "AutoFit",
    "location_id": 1034960394263,
    "location_type": "item",
    "quantity": 1,
    "type_id": 15331
  },
  {
    "is_singleton": false,
    "item_id": 1020504425020,
    "location_flag": "MedSlot2",
    "location_id": 1035013289119,
    "location_type": "item",
    "quantity": 1,
    "type_id": 28999
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009633950218,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11302
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010224088339,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 60341
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012006529262,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 60341
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015046121346,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1404
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015138887628,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1404
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1017503540203,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1404
  },
  {
    "is_singleton": false,
    "item_id": 1035176416667,
    "location_flag": "Hangar",
    "location_id": 60010699,
    "location_type": "station",
    "quantity": 430,
    "type_id": 17452
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009550215725,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11302
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012524448015,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1446
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010220961973,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 60341
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015046088119,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2604
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1017503540400,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2604
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1017503540410,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2604
  },
  {
    "is_singleton": true,
    "item_id": 1018046221823,
    "location_flag": "AutoFit",
    "location_id": 1017096452525,
    "location_type": "item",
    "quantity": 1,
    "type_id": 17332
  },
  {
    "is_singleton": false,
    "item_id": 1019486641151,
    "location_flag": "AutoFit",
    "location_id": 1035025574825,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11579
  },
  {
    "is_singleton": false,
    "item_id": 1007330078725,
    "location_flag": "Hangar",
    "location_id": 60015136,
    "location_type": "station",
    "quantity": 136,
    "type_id": 15625
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011464995858,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11306
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012770652264,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1245
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1032783074211,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2487
  },
  {
    "is_singleton": true,
    "item_id": 1017543546538,
    "location_flag": "AutoFit",
    "location_id": 1017096452525,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2174
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1008867024222,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1096
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011703411739,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1196
  },
  {
    "is_singleton": false,
    "item_id": 1035019868454,
    "location_flag": "AutoFit",
    "location_id": 1035124094434,
    "location_type": "item",
    "quantity": 3,
    "type_id": 32787
  },
  {
    "is_singleton": false,
    "item_id": 1035410655658,
    "location_flag": "AutoFit",
    "location_id": 1035425458067,
    "location_type": "item",
    "quantity": 1,
    "type_id": 37611
  },
  {
    "is_singleton": false,
    "item_id": 1012005853908,
    "location_flag": "Hangar",
    "location_id": 60015136,
    "location_type": "station",
    "quantity": 59,
    "type_id": 241
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012006529280,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 60341
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012565456080,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2290
  },
  {
    "is_singleton": true,
    "item_id": 1018860328740,
    "location_flag": "LoSlot0",
    "location_id": 1019123153786,
    "location_type": "item",
    "quantity": 1,
    "type_id": 10998
  },
  {
    "is_singleton": true,
    "item_id": 1035152556866,
    "location_flag": "Cargo",
    "location_id": 1023946861628,
    "location_type": "item",
    "quantity": 1,
    "type_id": 23079
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1017503540190,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1404
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1035017913325,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 17334
  },
  {
    "is_singleton": true,
    "item_id": 1035124092837,
    "location_flag": "HiSlot5",
    "location_id": 1035122390165,
    "location_type": "item",
    "quantity": 1,
    "type_id": 24348
  },
  {
    "is_singleton": true,
    "item_id": 1035491153577,
    "location_flag": "HiSlot0",
    "location_id": 1035491153575,
    "location_type": "item",
    "quantity": 1,
    "type_id": 3634
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012770652324,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2604
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012770652387,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1318
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015093622764,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2604
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1017503540825,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1245
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1008867024181,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1096
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011816287974,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1129
  },
  {
    "is_singleton": true,
    "item_id": 1035136966894,
    "location_flag": "MedSlot2",
    "location_id": 1035103634311,
    "location_type": "item",
    "quantity": 1,
    "type_id": 10868
  },
  {
    "is_singleton": false,
    "item_id": 1035019844946,
    "location_flag": "AutoFit",
    "location_id": 1035124094434,
    "location_type": "item",
    "quantity": 5,
    "type_id": 15508
  },
  {
    "is_singleton": false,
    "item_id": 1035185249176,
    "location_flag": "AutoFit",
    "location_id": 1035425458067,
    "location_type": "item",
    "quantity": 1,
    "type_id": 19814
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1035520932761,
    "location_flag": "Hangar",
    "location_id": 1036030944542,
    "location_type": "item",
    "quantity": 1,
    "type_id": 31359
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011772708295,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 22543
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012166363416,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1245
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012208988106,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1404
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012694074161,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1404
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015093622779,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2604
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015138739152,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11613
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015138887756,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11358
  },
  {
    "is_singleton": true,
    "item_id": 1011792967773,
    "location_flag": "AutoFit",
    "location_id": 1019312782218,
    "location_type": "item",
    "quantity": 1,
    "type_id": 31056
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009550214888,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11306
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012770652310,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2604
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015093622030,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11613
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015093622722,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1404
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015217369377,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1245
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009550214897,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11306
  },
  {
    "is_singleton": false,
    "item_id": 1012078651929,
    "location_flag": "Hangar",
    "location_id": 60015136,
    "location_type": "station",
    "quantity": 65,
    "type_id": 252
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011861112798,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1540
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015093622037,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11613
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015138723498,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1245
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1017503541072,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11358
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009483062175,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011772708069,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1196
  },
  {
    "is_singleton": true,
    "item_id": 1035152469078,
    "location_flag": "HiSlot1",
    "location_id": 1035152469075,
    "location_type": "item",
    "quantity": 1,
    "type_id": 3651
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010306934338,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 60341
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012770617689,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1404
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015046121356,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1404
  },
  {
    "is_singleton": true,
    "item_id": 1010293429592,
    "location_flag": "AutoFit",
    "location_id": 1019312782218,
    "location_type": "item",
    "quantity": 1,
    "type_id": 31731
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012139393226,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2332
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012627069348,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 12275
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009696268056,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_singleton": false,
    "item_id": 1035153573243,
    "location_flag": "Hangar",
    "location_id": 60004588,
    "location_type": "station",
    "quantity": 10,
    "type_id": 23079
  },
  {
    "is_singleton": true,
    "item_id": 1035136728939,
    "location_flag": "Hangar",
    "location_id": 60008494,
    "location_type": "station",
    "quantity": 1,
    "type_id": 596
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009357548048,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11302
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012111048591,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1318
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012139393214,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2332
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012512986443,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11358
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015046088176,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1245
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015138887633,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1404
  },
  {
    "is_singleton": true,
    "item_id": 1032792565052,
    "location_flag": "AutoFit",
    "location_id": 1017096452525,
    "location_type": "item",
    "quantity": 1,
    "type_id": 32867
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009730695131,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1034992525140,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 17332
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010281219737,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11302
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010395119535,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11306
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012111048592,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1318
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015138887626,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1404
  },
  {
    "is_singleton": true,
    "item_id": 1009000189896,
    "location_flag": "AutoFit",
    "location_id": 1017096452525,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11306
  },
  {
    "is_singleton": true,
    "item_id": 1035152539370,
    "location_flag": "HiSlot1",
    "location_id": 1023946861628,
    "location_type": "item",
    "quantity": 1,
    "type_id": 6719
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010306934324,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 60341
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012283194823,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2294
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012524445309,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 828
  },
  {
    "is_singleton": true,
    "item_id": 1035143887575,
    "location_flag": "HiSlot0",
    "location_id": 1035143887571,
    "location_type": "item",
    "quantity": 1,
    "type_id": 3634
  },
  {
    "is_singleton": true,
    "item_id": 1035305136988,
    "location_flag": "Hangar",
    "location_id": 60010066,
    "location_type": "station",
    "quantity": 1,
    "type_id": 23085
  },
  {
    "is_singleton": true,
    "item_id": 1017783038524,
    "location_flag": "Hangar",
    "location_id": 60014089,
    "location_type": "station",
    "quantity": 1,
    "type_id": 596
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009732838979,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012451147900,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11358
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012770652393,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1318
  },
  {
    "is_singleton": false,
    "item_id": 1034962169074,
    "location_flag": "MedSlot3",
    "location_id": 1034962148671,
    "location_type": "item",
    "quantity": 1,
    "type_id": 29001
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1012749004468,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1446
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1032766184870,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 836
  },
  {
    "is_singleton": false,
    "item_id": 1035311810816,
    "location_flag": "Hangar",
    "location_id": 60008500,
    "location_type": "station",
    "quantity": 100,
    "type_id": 224
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011861112806,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1540
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015093622769,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2604
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1015093622781,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2604
  },
  {
    "is_singleton": true,
    "item_id": 1035152764908,
    "location_flag": "LoSlot3",
    "location_id": 1023946861628,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11325
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010627187728,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11304
  },
  {
    "is_singleton": true,
    "item_id": 1005458604009,
    "location_flag": "AutoFit",
    "location_id": 1019312782218,
    "location_type": "item",
    "quantity": 1,
    "type_id": 31741
  },
  {
    "is_singleton": true,
    "item_id": 1005458706867,
    "location_flag": "AutoFit",
    "location_id": 1019312782218,
    "location_type": "item",
    "quantity": 1,
    "type_id": 31118
  },
  {
    "is_singleton": true,
    "item_id": 1005458732399,
    "location_flag": "AutoFit",
    "location_id": 1019312782218,
    "location_type": "item",
    "quantity": 1,
    "type_id": 31395
  },
  {
    "is_singleton": false,
    "item_id": 1006615033820,
    "location_flag": "Hangar",
    "location_id": 60015136,
    "location_type": "station",
    "quantity": 50,
    "type_id": 15628
  },
  {
    "is_singleton": false,
    "item_id": 1007330071613,
    "location_flag": "Hangar",
    "location_id": 60015136,
    "location_type": "station",
    "quantity": 28,
    "type_id": 15626
  },
  {
    "is_singleton": false,
    "item_id": 1007330095037,
    "location_flag": "Hangar",
    "location_id": 60015136,
    "location_type": "station",
    "quantity": 11,
    "type_id": 15627
  },
  {
    "is_singleton": true,
    "item_id": 1007781976194,
    "location_flag": "AutoFit",
    "location_id": 1034932618957,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11613
  },
  {
    "is_singleton": true,
    "item_id": 1007793489881,
    "location_flag": "AutoFit",
    "location_id": 1017096452525,
    "location_type": "item",
    "quantity": 1,
    "type_id": 22543
  },
  {
    "is_singleton": true,
    "item_id": 1007955096389,
    "location_flag": "Hangar",
    "location_id": 1036030944542,
    "location_type": "item",
    "quantity": 1,
    "type_id": 31359
  },
  {
    "is_singleton": true,
    "item_id": 1007955263515,
    "location_flag": "AutoFit",
    "location_id": 1017096452525,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2332
  },
  {
    "is_singleton": true,
    "item_id": 1008088504177,
    "location_flag": "AutoFit",
    "location_id": 1019312782218,
    "location_type": "item",
    "quantity": 1,
    "type_id": 31154
  },
  {
    "is_singleton": true,
    "item_id": 1008144844465,
    "location_flag": "AutoFit",
    "location_id": 1019312782218,
    "location_type": "item",
    "quantity": 1,
    "type_id": 31178
  },
  {
    "is_singleton": true,
    "item_id": 1008144850038,
    "location_flag": "AutoFit",
    "location_id": 1019312782218,
    "location_type": "item",
    "quantity": 1,
    "type_id": 31106
  },
  {
    "is_singleton": true,
    "item_id": 1008259013539,
    "location_flag": "AutoFit",
    "location_id": 1017096452525,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11613
  },
  {
    "is_singleton": true,
    "item_id": 1008267362127,
    "location_flag": "AutoFit",
    "location_id": 1017096452525,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1318
  },
  {
    "is_singleton": true,
    "item_id": 1008275595570,
    "location_flag": "AutoFit",
    "location_id": 1017096452525,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2604
  },
  {
    "is_singleton": true,
    "item_id": 1008281222909,
    "location_flag": "AutoFit",
    "location_id": 1017096452525,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_singleton": true,
    "item_id": 1008656218147,
    "location_flag": "AutoFit",
    "location_id": 1019312782218,
    "location_type": "item",
    "quantity": 1,
    "type_id": 31801
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1008867024177,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1096
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1008867024180,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1096
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1008867024185,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1096
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1008867024186,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1096
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1008867024223,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1096
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1008867024225,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1096
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1008867024230,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1096
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1008867024232,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1096
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1008867024234,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1096
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1008867024240,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1096
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1008867024243,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1096
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1008867024246,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1096
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1008884857657,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1096
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1008884857658,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1096
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1008884857660,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1096
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1008884857662,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1096
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1008884857665,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1096
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1008884857666,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1096
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1008884857668,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1096
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1008884857669,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1096
  },
  {
    "is_singleton": true,
    "item_id": 1009000188608,
    "location_flag": "AutoFit",
    "location_id": 1017096452525,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11304
  },
  {
    "is_singleton": true,
    "item_id": 1009000191064,
    "location_flag": "AutoFit",
    "location_id": 1017096452525,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11278
  },
  {
    "is_singleton": true,
    "item_id": 1009126438041,
    "location_flag": "AutoFit",
    "location_id": 1017096452525,
    "location_type": "item",
    "quantity": 1,
    "type_id": 24349
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009357548037,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11302
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009357548105,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11278
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009412208048,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11302
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009412208056,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11302
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009412208059,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11302
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009412208061,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11302
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009412208145,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11278
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009412208149,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11278
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009412208150,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11278
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009412208155,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11278
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009412208158,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11278
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009412208159,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11278
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009412208161,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11278
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009483062165,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009483062171,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009483062173,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009483062177,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009483062219,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009483062222,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009483062224,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009483062350,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 24349
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009483062356,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 24349
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009483062360,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 24349
  },
  {
    "is_singleton": true,
    "item_id": 1009546716692,
    "location_flag": "AutoFit",
    "location_id": 1019312782218,
    "location_type": "item",
    "quantity": 1,
    "type_id": 31659
  },
  {
    "is_singleton": true,
    "item_id": 1009546735208,
    "location_flag": "AutoFit",
    "location_id": 1017096452525,
    "location_type": "item",
    "quantity": 1,
    "type_id": 60341
  },
  {
    "is_singleton": true,
    "item_id": 1009546740475,
    "location_flag": "AutoFit",
    "location_id": 1017096452525,
    "location_type": "item",
    "quantity": 1,
    "type_id": 60343
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009550214884,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11306
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009550214885,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11306
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009550214886,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11306
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009550214892,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11306
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009550214896,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11306
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009550214899,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11306
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009550214901,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11306
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009550215692,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11302
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009550215693,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11302
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009550215696,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11302
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009550215697,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11302
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009550215701,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11302
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009550215705,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11302
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009550215711,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11302
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009550215714,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11302
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009550215718,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11302
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009550215721,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11302
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009550215728,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11302
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009550215730,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11302
  },
  {
    "is_singleton": true,
    "item_id": 1009580431625,
    "location_flag": "AutoFit",
    "location_id": 1017096452525,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1404
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009624924645,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009624924646,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009624924649,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009633950195,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11302
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009633950197,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11302
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009633950200,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11302
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009633950201,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11302
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009633950203,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11302
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009633950205,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11302
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009633950208,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11302
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009633950209,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11302
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009633950211,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11302
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009633950213,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11302
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009633950217,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11302
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009696268040,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009696268046,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009696268050,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009696268055,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_singleton": true,
    "item_id": 1009696273668,
    "location_flag": "AutoFit",
    "location_id": 1017096452525,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11358
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009730695113,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009730695121,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009730695130,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009730695133,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009730695135,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009732838978,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009732852126,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009732852129,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009732852234,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009732852239,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009732852243,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009732852246,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009770069848,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009770069849,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009770069960,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009770069965,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009770069967,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009770070145,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009770070148,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009770070171,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009770070191,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009770070193,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009770070200,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009770070297,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009770070308,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009770070310,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009770070311,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1009778838283,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 60341
  },
  {
    "is_singleton": true,
    "item_id": 1009779979322,
    "location_flag": "AutoFit",
    "location_id": 1017096452525,
    "location_type": "item",
    "quantity": 1,
    "type_id": 60341
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010220961975,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 60341
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010220961979,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 60341
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010220962010,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 60341
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010220962012,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 60341
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010224088303,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 60343
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010224088304,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 60343
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010224088329,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 60341
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010224088333,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 60341
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010224088334,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 60341
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010224088336,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 60341
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010224088337,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 60341
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010224088338,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 60341
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010224088343,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 60341
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010224088348,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 60341
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010224088467,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010224088469,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010224088471,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010224088472,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010224088473,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010224088475,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010224088477,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010224088485,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010224088486,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010224088491,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010224088500,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010224088503,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010224088504,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1305
  },
  {
    "is_singleton": true,
    "item_id": 1010251968393,
    "location_flag": "AutoFit",
    "location_id": 1017096452525,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2047
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010281219739,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11302
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010281219741,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11302
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010281219744,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11302
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010281219745,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11302
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010281219747,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11302
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010281219749,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11302
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010281219752,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11302
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010281219753,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11302
  },
  {
    "is_singleton": true,
    "item_id": 1010297320655,
    "location_flag": "AutoFit",
    "location_id": 1019312782218,
    "location_type": "item",
    "quantity": 1,
    "type_id": 31719
  },
  {
    "is_singleton": true,
    "item_id": 1010297328918,
    "location_flag": "AutoFit",
    "location_id": 1017096452525,
    "location_type": "item",
    "quantity": 1,
    "type_id": 824
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010306934323,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 60341
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010306934325,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 60341
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010306934326,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 60341
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010306934330,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 60341
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010306934332,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 60341
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010306934334,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 60341
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010306934342,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 60341
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010306934347,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 60341
  },
  {
    "is_singleton": true,
    "item_id": 1010315912685,
    "location_flag": "AutoFit",
    "location_id": 1017096452525,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1245
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010395119501,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11306
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010395119502,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11306
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010395119504,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11306
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010395119507,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11306
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010395119509,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11306
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010395119512,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11306
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010395119517,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11306
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010395119518,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11306
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010395119520,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11306
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010395119523,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11306
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010395119525,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11306
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010395119529,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11306
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010395119534,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11306
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010627187717,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11304
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010627187720,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11304
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010627187721,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11304
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010627187723,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11304
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010627187729,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11304
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010627187730,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11304
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010627187735,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11304
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010627187741,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11304
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010627187746,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11304
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010627187750,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11304
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010627187752,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11304
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010679316451,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2047
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010679316452,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2047
  },
  {
    "is_singleton": false,
    "item_id": 1010720520716,
    "location_flag": "AutoFit",
    "location_id": 1035025574825,
    "location_type": "item",
    "quantity": 1,
    "type_id": 3310
  },
  {
    "is_singleton": false,
    "item_id": 1010720528758,
    "location_flag": "AutoFit",
    "location_id": 1035025574825,
    "location_type": "item",
    "quantity": 1,
    "type_id": 3453
  },
  {
    "is_singleton": false,
    "item_id": 1010720579043,
    "location_flag": "AutoFit",
    "location_id": 1035025574825,
    "location_type": "item",
    "quantity": 1,
    "type_id": 3305
  },
  {
    "is_singleton": true,
    "item_id": 1010848366291,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "location_type": "station",
    "quantity": 1,
    "type_id": 3467
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1010881151133,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1540
  },
  {
    "is_singleton": true,
    "item_id": 1011403947938,
    "location_flag": "AutoFit",
    "location_id": 1017096452525,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11870
  },
  {
    "is_singleton": true,
    "item_id": 1011403949570,
    "location_flag": "AutoFit",
    "location_id": 1017096452525,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11859
  },
  {
    "is_singleton": true,
    "item_id": 1011403951674,
    "location_flag": "AutoFit",
    "location_id": 1017096452525,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11889
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011464995811,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1196
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011464995813,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1196
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011464995814,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1196
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011464995817,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1196
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011464995853,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11306
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011464995857,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11306
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011464995863,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11306
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011464995866,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11306
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011464995868,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11306
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011464995870,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11306
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011464995871,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11306
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011464995872,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11306
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011464995873,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11306
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011464995879,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11306
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011703411730,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1196
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011703411732,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1196
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011703411735,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1196
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011703411741,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1196
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011703411936,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1540
  },
  {
    "is_singleton": true,
    "item_id": 1011723760497,
    "location_flag": "AutoFit",
    "location_id": 1017096452525,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2294
  },
  {
    "is_singleton": true,
    "item_id": 1011723764297,
    "location_flag": "AutoFit",
    "location_id": 1017096452525,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2290
  },
  {
    "is_singleton": true,
    "item_id": 1011723768183,
    "location_flag": "AutoFit",
    "location_id": 1017096452525,
    "location_type": "item",
    "quantity": 1,
    "type_id": 2296
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011772708018,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11613
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011772708020,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11613
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011772708023,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11613
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011772708026,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11613
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011772708028,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11613
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011772708034,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11613
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011772708036,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11613
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011772708039,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 11613
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011772708065,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1196
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011772708067,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1196
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011772708076,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1196
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011772708077,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1196
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011772708079,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1196
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011772708083,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1196
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011772708086,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1196
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011772708089,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1196
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011772708092,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1196
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011772708095,
    "location_flag": "AutoFit",
    "location_id": 1010848366291,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1196
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011772708114,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1540
  },
  {
    "is_blueprint_copy": true,
    "is_singleton": true,
    "item_id": 1011772708115,
    "location_flag": "AutoFit",
    "location_id": 1034962800851,
    "location_type": "item",
    "quantity": 1,
    "type_id": 1540
  }
]

# Blueprints
curl -X GET "https://esi.evetech.net/latest/characters/93813310/blueprints/?datasource=tranquility&page=1" -H "accept: application/json" -H "authorization: Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6IkpXVC1TaWduYXR1cmUtS2V5IiwidHlwIjoiSldUIn0.eyJzY3AiOlsiZXNpLWNoYXJhY3RlcnMucmVhZF9ibHVlcHJpbnRzLnYxIiwiZXNpLWFzc2V0cy5yZWFkX2Fzc2V0cy52MSIsImVzaS1hc3NldHMucmVhZF9jb3Jwb3JhdGlvbl9hc3NldHMudjEiXSwianRpIjoiOWM0NTQ1OWMtMDQ3MS00YjcyLTliZmItMTM3MzY2NTYwZTBjIiwia2lkIjoiSldULVNpZ25hdHVyZS1LZXkiLCJzdWIiOiJDSEFSQUNURVI6RVZFOjkzODEzMzEwIiwiYXpwIjoiNjgzMDg0YWI1Zjg4NDhkNGIxODc0NjJhYzNiOTc2NzciLCJ0ZW5hbnQiOiJ0cmFucXVpbGl0eSIsInRpZXIiOiJsaXZlIiwicmVnaW9uIjoid29ybGQiLCJhdWQiOlsiNjgzMDg0YWI1Zjg4NDhkNGIxODc0NjJhYzNiOTc2NzciLCJFVkUgT25saW5lIl0sIm5hbWUiOiJQZXJpY28gVHVlcnRvIiwib3duZXIiOiJRc2lrT2pXUFFERnAzM1hucEl1VzhnM0Z5eFE9IiwiZXhwIjoxNzE2OTE0OTIwLCJpYXQiOjE3MTY5MTM3MjAsImlzcyI6Imh0dHBzOi8vbG9naW4uZXZlb25saW5lLmNvbSJ9.QFHPL2MYxxUA3ILmVH0RjSxg4gGzVxauObGAYWHo1M5WSzKBsSwhx9febDo5C-0k4xbKCTuctPqN9R7GqpSR6R3jie9Rx2J-Ae_19T4hx91hq3H6mPsppJ6aXVxpfNbLbX2vya4VCO7CtDSeCWA6DBh9ofKN_QxdBKuIP1NUMpxENELsKkeCGP2r2ByFioDtrqVtnJCiHcS_DmrmFqYOwyDQyqOBBeE5V-TXDOxV3UbM7d2M6onOIlRwr7Ezv5IsePRygt2hTBB3eWVjR8TTjOS3L5svrVByhWDYIQLwi_cgx8uwfcJr5R3VOfv0_UAVU-n8k_pu0_6w0ss-KerCCQ" -H "Cache-Control: no-cache"

[
  {
    "item_id": 1004406235971,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -1,
    "runs": -1,
    "time_efficiency": 20,
    "type_id": 785
  },
  {
    "item_id": 1005458604009,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -1,
    "runs": -1,
    "time_efficiency": 20,
    "type_id": 31741
  },
  {
    "item_id": 1005458706867,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -1,
    "runs": -1,
    "time_efficiency": 20,
    "type_id": 31118
  },
  {
    "item_id": 1005458732399,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -1,
    "runs": -1,
    "time_efficiency": 20,
    "type_id": 31395
  },
  {
    "item_id": 1007781976194,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -1,
    "runs": -1,
    "time_efficiency": 20,
    "type_id": 11613
  },
  {
    "item_id": 1007793489881,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -1,
    "runs": -1,
    "time_efficiency": 20,
    "type_id": 22543
  },
  {
    "item_id": 1007955093774,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -1,
    "runs": -1,
    "time_efficiency": 20,
    "type_id": 31717
  },
  {
    "item_id": 1007955096389,
    "location_flag": "AssetSafety",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -1,
    "runs": -1,
    "time_efficiency": 20,
    "type_id": 31359
  },
  {
    "item_id": 1007955263515,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -1,
    "runs": -1,
    "time_efficiency": 20,
    "type_id": 2332
  },
  {
    "item_id": 1008088504177,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -1,
    "runs": -1,
    "time_efficiency": 18,
    "type_id": 31154
  },
  {
    "item_id": 1008144844465,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -1,
    "runs": -1,
    "time_efficiency": 18,
    "type_id": 31178
  },
  {
    "item_id": 1008144850038,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -1,
    "runs": -1,
    "time_efficiency": 18,
    "type_id": 31106
  },
  {
    "item_id": 1008259013539,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -1,
    "runs": -1,
    "time_efficiency": 20,
    "type_id": 11613
  },
  {
    "item_id": 1008266912283,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -1,
    "runs": -1,
    "time_efficiency": 20,
    "type_id": 1318
  },
  {
    "item_id": 1008267362127,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -1,
    "runs": -1,
    "time_efficiency": 20,
    "type_id": 1318
  },
  {
    "item_id": 1008275595570,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -1,
    "runs": -1,
    "time_efficiency": 20,
    "type_id": 2604
  },
  {
    "item_id": 1008281222909,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -1,
    "runs": -1,
    "time_efficiency": 20,
    "type_id": 1305
  },
  {
    "item_id": 1008656218147,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -1,
    "runs": -1,
    "time_efficiency": 16,
    "type_id": 31801
  },
  {
    "item_id": 1008664394509,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -1,
    "runs": -1,
    "time_efficiency": 20,
    "type_id": 1096
  },
  {
    "item_id": 1008999803278,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -1,
    "runs": -1,
    "time_efficiency": 20,
    "type_id": 828
  },
  {
    "item_id": 1008999826427,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -1,
    "runs": -1,
    "time_efficiency": 20,
    "type_id": 11616
  },
  {
    "item_id": 1008999830819,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -1,
    "runs": -1,
    "time_efficiency": 20,
    "type_id": 1129
  },
  {
    "item_id": 1009000185979,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -1,
    "runs": -1,
    "time_efficiency": 20,
    "type_id": 11302
  },
  {
    "item_id": 1009000188608,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -1,
    "runs": -1,
    "time_efficiency": 20,
    "type_id": 11304
  },
  {
    "item_id": 1009000189896,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -1,
    "runs": -1,
    "time_efficiency": 20,
    "type_id": 11306
  },
  {
    "item_id": 1009000191064,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -1,
    "runs": -1,
    "time_efficiency": 20,
    "type_id": 11278
  },
  {
    "item_id": 1009000339569,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -1,
    "runs": -1,
    "time_efficiency": 20,
    "type_id": 1196
  },
  {
    "item_id": 1009124016259,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -1,
    "runs": -1,
    "time_efficiency": 20,
    "type_id": 24349
  },
  {
    "item_id": 1009124260755,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -1,
    "runs": -1,
    "time_efficiency": 20,
    "type_id": 60341
  },
  {
    "item_id": 1009126438041,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -1,
    "runs": -1,
    "time_efficiency": 20,
    "type_id": 24349
  },
  {
    "item_id": 1009144613825,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -1,
    "runs": -1,
    "time_efficiency": 20,
    "type_id": 60341
  },
  {
    "item_id": 1009546716692,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 8,
    "quantity": -1,
    "runs": -1,
    "time_efficiency": 16,
    "type_id": 31659
  },
  {
    "item_id": 1009546735208,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -1,
    "runs": -1,
    "time_efficiency": 20,
    "type_id": 60341
  },
  {
    "item_id": 1009546740475,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -1,
    "runs": -1,
    "time_efficiency": 20,
    "type_id": 60343
  },
  {
    "item_id": 1009580431625,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -1,
    "runs": -1,
    "time_efficiency": 20,
    "type_id": 1404
  },
  {
    "item_id": 1009580504065,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -1,
    "runs": -1,
    "time_efficiency": 20,
    "type_id": 11358
  },
  {
    "item_id": 1009696273668,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -1,
    "runs": -1,
    "time_efficiency": 20,
    "type_id": 11358
  },
  {
    "item_id": 1009779979322,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -1,
    "runs": -1,
    "time_efficiency": 20,
    "type_id": 60341
  },
  {
    "item_id": 1009780033706,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -1,
    "runs": -1,
    "time_efficiency": 18,
    "type_id": 31743
  },
  {
    "item_id": 1010251968393,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -1,
    "runs": -1,
    "time_efficiency": 20,
    "type_id": 2047
  },
  {
    "item_id": 1010293429592,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -1,
    "runs": -1,
    "time_efficiency": 18,
    "type_id": 31731
  },
  {
    "item_id": 1010297320655,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -1,
    "runs": -1,
    "time_efficiency": 16,
    "type_id": 31719
  },
  {
    "item_id": 1010297328918,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -1,
    "runs": -1,
    "time_efficiency": 20,
    "type_id": 824
  },
  {
    "item_id": 1010315912685,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -1,
    "runs": -1,
    "time_efficiency": 20,
    "type_id": 1245
  },
  {
    "item_id": 1010315913512,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -1,
    "runs": -1,
    "time_efficiency": 20,
    "type_id": 1540
  },
  {
    "item_id": 1011403938011,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -1,
    "runs": -1,
    "time_efficiency": 20,
    "type_id": 11873
  },
  {
    "item_id": 1011403947938,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -1,
    "runs": -1,
    "time_efficiency": 20,
    "type_id": 11870
  },
  {
    "item_id": 1011403949570,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -1,
    "runs": -1,
    "time_efficiency": 20,
    "type_id": 11859
  },
  {
    "item_id": 1011403951674,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -1,
    "runs": -1,
    "time_efficiency": 20,
    "type_id": 11889
  },
  {
    "item_id": 1011723760497,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -1,
    "runs": -1,
    "time_efficiency": 20,
    "type_id": 2294
  },
  {
    "item_id": 1011723764297,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -1,
    "runs": -1,
    "time_efficiency": 20,
    "type_id": 2290
  },
  {
    "item_id": 1011723766731,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -1,
    "runs": -1,
    "time_efficiency": 20,
    "type_id": 2292
  },
  {
    "item_id": 1011723768183,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -1,
    "runs": -1,
    "time_efficiency": 20,
    "type_id": 2296
  },
  {
    "item_id": 1011792961782,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -1,
    "runs": -1,
    "time_efficiency": 16,
    "type_id": 31156
  },
  {
    "item_id": 1011792967773,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -1,
    "runs": -1,
    "time_efficiency": 16,
    "type_id": 31056
  },
  {
    "item_id": 1011792973891,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -1,
    "runs": -1,
    "time_efficiency": 18,
    "type_id": 31803
  },
  {
    "item_id": 1011792976290,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -1,
    "runs": -1,
    "time_efficiency": 16,
    "type_id": 31120
  },
  {
    "item_id": 1011792978875,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -1,
    "runs": -1,
    "time_efficiency": 18,
    "type_id": 31361
  },
  {
    "item_id": 1011792981698,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -1,
    "runs": -1,
    "time_efficiency": 20,
    "type_id": 886
  },
  {
    "item_id": 1011792982617,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -1,
    "runs": -1,
    "time_efficiency": 20,
    "type_id": 1137
  },
  {
    "item_id": 1011792983394,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -1,
    "runs": -1,
    "time_efficiency": 20,
    "type_id": 1161
  },
  {
    "item_id": 1012030853635,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -1,
    "runs": -1,
    "time_efficiency": 20,
    "type_id": 11891
  },
  {
    "item_id": 1012030859567,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -1,
    "runs": -1,
    "time_efficiency": 20,
    "type_id": 11887
  },
  {
    "item_id": 1012032011784,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -1,
    "runs": -1,
    "time_efficiency": 16,
    "type_id": 31074
  },
  {
    "item_id": 1012032017248,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -1,
    "runs": -1,
    "time_efficiency": 20,
    "type_id": 31373
  },
  {
    "item_id": 1012284182091,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -1,
    "runs": -1,
    "time_efficiency": 20,
    "type_id": 1446
  },
  {
    "item_id": 1012284186591,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -1,
    "runs": -1,
    "time_efficiency": 20,
    "type_id": 12275
  },
  {
    "item_id": 1012284189372,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -1,
    "runs": -1,
    "time_efficiency": 20,
    "type_id": 9945
  },
  {
    "item_id": 1012450601055,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -1,
    "runs": -1,
    "time_efficiency": 20,
    "type_id": 11284
  },
  {
    "item_id": 1012579169651,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -1,
    "runs": -1,
    "time_efficiency": 16,
    "type_id": 25949
  },
  {
    "item_id": 1012579241327,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -1,
    "runs": -1,
    "time_efficiency": 20,
    "type_id": 3840
  },
  {
    "item_id": 1012579567975,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -1,
    "runs": -1,
    "time_efficiency": 16,
    "type_id": 31180
  },
  {
    "item_id": 1012589640333,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -1,
    "runs": -1,
    "time_efficiency": 20,
    "type_id": 3830
  },
  {
    "item_id": 1012589645910,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -1,
    "runs": -1,
    "time_efficiency": 20,
    "type_id": 826
  },
  {
    "item_id": 1012598006312,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -1,
    "runs": -1,
    "time_efficiency": 16,
    "type_id": 31108
  },
  {
    "item_id": 1012620512207,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -1,
    "runs": -1,
    "time_efficiency": 20,
    "type_id": 31791
  },
  {
    "item_id": 1008867024177,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1096
  },
  {
    "item_id": 1008867024180,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1096
  },
  {
    "item_id": 1008867024181,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1096
  },
  {
    "item_id": 1008867024185,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1096
  },
  {
    "item_id": 1008867024186,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1096
  },
  {
    "item_id": 1008867024187,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1096
  },
  {
    "item_id": 1008867024222,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1096
  },
  {
    "item_id": 1008867024223,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1096
  },
  {
    "item_id": 1008867024224,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1096
  },
  {
    "item_id": 1008867024225,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1096
  },
  {
    "item_id": 1008867024230,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 258,
    "time_efficiency": 0,
    "type_id": 1096
  },
  {
    "item_id": 1008867024232,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1096
  },
  {
    "item_id": 1008867024234,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1096
  },
  {
    "item_id": 1008867024235,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1096
  },
  {
    "item_id": 1008867024237,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1096
  },
  {
    "item_id": 1008867024240,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1096
  },
  {
    "item_id": 1008867024242,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1096
  },
  {
    "item_id": 1008867024243,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1096
  },
  {
    "item_id": 1008867024244,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1096
  },
  {
    "item_id": 1008867024246,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1096
  },
  {
    "item_id": 1008884857657,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1096
  },
  {
    "item_id": 1008884857658,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1096
  },
  {
    "item_id": 1008884857659,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1096
  },
  {
    "item_id": 1008884857660,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1096
  },
  {
    "item_id": 1008884857662,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1096
  },
  {
    "item_id": 1008884857664,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1096
  },
  {
    "item_id": 1008884857665,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1096
  },
  {
    "item_id": 1008884857666,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1096
  },
  {
    "item_id": 1008884857668,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1096
  },
  {
    "item_id": 1008884857669,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1096
  },
  {
    "item_id": 1008884857671,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 150,
    "time_efficiency": 0,
    "type_id": 1096
  },
  {
    "item_id": 1009357548037,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11302
  },
  {
    "item_id": 1009357548048,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11302
  },
  {
    "item_id": 1009357548049,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11302
  },
  {
    "item_id": 1009357548094,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11278
  },
  {
    "item_id": 1009357548105,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11278
  },
  {
    "item_id": 1009412208048,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11302
  },
  {
    "item_id": 1009412208051,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11302
  },
  {
    "item_id": 1009412208054,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11302
  },
  {
    "item_id": 1009412208056,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11302
  },
  {
    "item_id": 1009412208059,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11302
  },
  {
    "item_id": 1009412208060,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11302
  },
  {
    "item_id": 1009412208061,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11302
  },
  {
    "item_id": 1009412208065,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11302
  },
  {
    "item_id": 1009412208066,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11302
  },
  {
    "item_id": 1009412208145,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11278
  },
  {
    "item_id": 1009412208149,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11278
  },
  {
    "item_id": 1009412208150,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11278
  },
  {
    "item_id": 1009412208154,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11278
  },
  {
    "item_id": 1009412208155,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11278
  },
  {
    "item_id": 1009412208156,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11278
  },
  {
    "item_id": 1009412208158,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11278
  },
  {
    "item_id": 1009412208159,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11278
  },
  {
    "item_id": 1009412208161,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11278
  },
  {
    "item_id": 1009483062165,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1009483062167,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1009483062171,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1009483062173,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1009483062175,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1009483062177,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1009483062219,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1009483062222,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1009483062224,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1009483062350,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 24349
  },
  {
    "item_id": 1009483062353,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 24349
  },
  {
    "item_id": 1009483062356,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 24349
  },
  {
    "item_id": 1009483062360,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 24349
  },
  {
    "item_id": 1009550214884,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11306
  },
  {
    "item_id": 1009550214885,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11306
  },
  {
    "item_id": 1009550214886,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11306
  },
  {
    "item_id": 1009550214888,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11306
  },
  {
    "item_id": 1009550214892,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11306
  },
  {
    "item_id": 1009550214894,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11306
  },
  {
    "item_id": 1009550214896,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11306
  },
  {
    "item_id": 1009550214897,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11306
  },
  {
    "item_id": 1009550214899,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11306
  },
  {
    "item_id": 1009550214900,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11306
  },
  {
    "item_id": 1009550214901,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11306
  },
  {
    "item_id": 1009550214903,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11306
  },
  {
    "item_id": 1009550215692,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11302
  },
  {
    "item_id": 1009550215693,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11302
  },
  {
    "item_id": 1009550215694,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11302
  },
  {
    "item_id": 1009550215696,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11302
  },
  {
    "item_id": 1009550215697,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11302
  },
  {
    "item_id": 1009550215698,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11302
  },
  {
    "item_id": 1009550215700,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11302
  },
  {
    "item_id": 1009550215701,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11302
  },
  {
    "item_id": 1009550215703,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11302
  },
  {
    "item_id": 1009550215705,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11302
  },
  {
    "item_id": 1009550215711,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11302
  },
  {
    "item_id": 1009550215714,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11302
  },
  {
    "item_id": 1009550215718,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11302
  },
  {
    "item_id": 1009550215721,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11302
  },
  {
    "item_id": 1009550215725,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11302
  },
  {
    "item_id": 1009550215728,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11302
  },
  {
    "item_id": 1009550215730,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11302
  },
  {
    "item_id": 1009624924645,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1009624924646,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1009624924647,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1009624924648,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1009624924649,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1009633950195,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11302
  },
  {
    "item_id": 1009633950197,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11302
  },
  {
    "item_id": 1009633950198,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11302
  },
  {
    "item_id": 1009633950200,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11302
  },
  {
    "item_id": 1009633950201,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11302
  },
  {
    "item_id": 1009633950202,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11302
  },
  {
    "item_id": 1009633950203,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11302
  },
  {
    "item_id": 1009633950204,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11302
  },
  {
    "item_id": 1009633950205,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11302
  },
  {
    "item_id": 1009633950208,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11302
  },
  {
    "item_id": 1009633950209,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11302
  },
  {
    "item_id": 1009633950210,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11302
  },
  {
    "item_id": 1009633950211,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11302
  },
  {
    "item_id": 1009633950213,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11302
  },
  {
    "item_id": 1009633950215,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11302
  },
  {
    "item_id": 1009633950217,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11302
  },
  {
    "item_id": 1009633950218,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11302
  },
  {
    "item_id": 1009696268040,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1009696268042,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1009696268044,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1009696268046,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1009696268048,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1009696268050,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1009696268051,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1009696268055,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1009696268056,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1009730695113,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1009730695116,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1009730695121,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1009730695128,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1009730695130,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1009730695131,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1009730695133,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1009730695135,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1009730695137,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1009730695139,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1009732838977,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1009732838978,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1009732838979,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1009732852123,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1009732852126,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1009732852129,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1009732852234,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1009732852236,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1009732852239,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1009732852242,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1009732852243,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1009732852246,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1009732852248,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1009770069848,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1009770069849,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1009770069958,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1009770069960,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1009770069965,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1009770069967,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1009770070145,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1009770070147,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1009770070148,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1009770070153,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1009770070171,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1009770070181,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1009770070191,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1009770070193,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1009770070196,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1009770070200,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1009770070297,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1009770070299,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1009770070301,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1009770070306,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1009770070308,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1009770070310,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1009770070311,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1009770070498,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1009770070501,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1009770070504,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1009778838283,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 100,
    "time_efficiency": 0,
    "type_id": 60341
  },
  {
    "item_id": 1009933398033,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 100,
    "time_efficiency": 0,
    "type_id": 60341
  },
  {
    "item_id": 1010220961967,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 100,
    "time_efficiency": 0,
    "type_id": 60341
  },
  {
    "item_id": 1010220961973,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 100,
    "time_efficiency": 0,
    "type_id": 60341
  },
  {
    "item_id": 1010220961974,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 100,
    "time_efficiency": 0,
    "type_id": 60341
  },
  {
    "item_id": 1010220961975,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 100,
    "time_efficiency": 0,
    "type_id": 60341
  },
  {
    "item_id": 1010220961979,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 100,
    "time_efficiency": 0,
    "type_id": 60341
  },
  {
    "item_id": 1010220962010,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 100,
    "time_efficiency": 0,
    "type_id": 60341
  },
  {
    "item_id": 1010220962012,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 100,
    "time_efficiency": 0,
    "type_id": 60341
  },
  {
    "item_id": 1010220962030,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 100,
    "time_efficiency": 0,
    "type_id": 60341
  },
  {
    "item_id": 1010224088303,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 100,
    "time_efficiency": 0,
    "type_id": 60343
  },
  {
    "item_id": 1010224088304,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 100,
    "time_efficiency": 0,
    "type_id": 60343
  },
  {
    "item_id": 1010224088305,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 100,
    "time_efficiency": 0,
    "type_id": 60343
  },
  {
    "item_id": 1010224088329,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 100,
    "time_efficiency": 0,
    "type_id": 60341
  },
  {
    "item_id": 1010224088333,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 100,
    "time_efficiency": 0,
    "type_id": 60341
  },
  {
    "item_id": 1010224088334,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 100,
    "time_efficiency": 0,
    "type_id": 60341
  },
  {
    "item_id": 1010224088335,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 100,
    "time_efficiency": 0,
    "type_id": 60341
  },
  {
    "item_id": 1010224088336,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 100,
    "time_efficiency": 0,
    "type_id": 60341
  },
  {
    "item_id": 1010224088337,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 100,
    "time_efficiency": 0,
    "type_id": 60341
  },
  {
    "item_id": 1010224088338,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 100,
    "time_efficiency": 0,
    "type_id": 60341
  },
  {
    "item_id": 1010224088339,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 100,
    "time_efficiency": 0,
    "type_id": 60341
  },
  {
    "item_id": 1010224088341,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 100,
    "time_efficiency": 0,
    "type_id": 60341
  },
  {
    "item_id": 1010224088343,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 100,
    "time_efficiency": 0,
    "type_id": 60341
  },
  {
    "item_id": 1010224088345,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 100,
    "time_efficiency": 0,
    "type_id": 60341
  },
  {
    "item_id": 1010224088347,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 100,
    "time_efficiency": 0,
    "type_id": 60341
  },
  {
    "item_id": 1010224088348,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 100,
    "time_efficiency": 0,
    "type_id": 60341
  },
  {
    "item_id": 1010224088467,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1010224088469,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1010224088470,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1010224088471,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1010224088472,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1010224088473,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1010224088474,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1010224088475,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1010224088476,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1010224088477,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1010224088478,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1010224088479,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1010224088482,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1010224088485,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1010224088486,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1010224088491,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1010224088500,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1010224088502,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1010224088503,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1010224088504,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1305
  },
  {
    "item_id": 1010281219737,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11302
  },
  {
    "item_id": 1010281219738,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11302
  },
  {
    "item_id": 1010281219739,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11302
  },
  {
    "item_id": 1010281219740,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11302
  },
  {
    "item_id": 1010281219741,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11302
  },
  {
    "item_id": 1010281219742,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11302
  },
  {
    "item_id": 1010281219743,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11302
  },
  {
    "item_id": 1010281219744,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11302
  },
  {
    "item_id": 1010281219745,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11302
  },
  {
    "item_id": 1010281219746,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11302
  },
  {
    "item_id": 1010281219747,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11302
  },
  {
    "item_id": 1010281219748,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11302
  },
  {
    "item_id": 1010281219749,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11302
  },
  {
    "item_id": 1010281219750,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11302
  },
  {
    "item_id": 1010281219751,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11302
  },
  {
    "item_id": 1010281219752,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11302
  },
  {
    "item_id": 1010281219753,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11302
  },
  {
    "item_id": 1010281219754,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11302
  },
  {
    "item_id": 1010306934323,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 100,
    "time_efficiency": 0,
    "type_id": 60341
  },
  {
    "item_id": 1010306934324,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 100,
    "time_efficiency": 0,
    "type_id": 60341
  },
  {
    "item_id": 1010306934325,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 100,
    "time_efficiency": 0,
    "type_id": 60341
  },
  {
    "item_id": 1010306934326,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 100,
    "time_efficiency": 0,
    "type_id": 60341
  },
  {
    "item_id": 1010306934328,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 100,
    "time_efficiency": 0,
    "type_id": 60341
  },
  {
    "item_id": 1010306934330,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 100,
    "time_efficiency": 0,
    "type_id": 60341
  },
  {
    "item_id": 1010306934332,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 100,
    "time_efficiency": 0,
    "type_id": 60341
  },
  {
    "item_id": 1010306934334,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 100,
    "time_efficiency": 0,
    "type_id": 60341
  },
  {
    "item_id": 1010306934335,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 100,
    "time_efficiency": 0,
    "type_id": 60341
  },
  {
    "item_id": 1010306934336,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 100,
    "time_efficiency": 0,
    "type_id": 60341
  },
  {
    "item_id": 1010306934338,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 100,
    "time_efficiency": 0,
    "type_id": 60341
  },
  {
    "item_id": 1010306934339,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 100,
    "time_efficiency": 0,
    "type_id": 60341
  },
  {
    "item_id": 1010306934341,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 100,
    "time_efficiency": 0,
    "type_id": 60341
  },
  {
    "item_id": 1010306934342,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 100,
    "time_efficiency": 0,
    "type_id": 60341
  },
  {
    "item_id": 1010306934345,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 100,
    "time_efficiency": 0,
    "type_id": 60341
  },
  {
    "item_id": 1010306934347,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 100,
    "time_efficiency": 0,
    "type_id": 60341
  },
  {
    "item_id": 1010306934349,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 100,
    "time_efficiency": 0,
    "type_id": 60341
  },
  {
    "item_id": 1010395119501,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 260,
    "time_efficiency": 0,
    "type_id": 11306
  },
  {
    "item_id": 1010395119502,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11306
  },
  {
    "item_id": 1010395119504,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11306
  },
  {
    "item_id": 1010395119507,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11306
  },
  {
    "item_id": 1010395119509,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11306
  },
  {
    "item_id": 1010395119510,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11306
  },
  {
    "item_id": 1010395119512,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11306
  },
  {
    "item_id": 1010395119514,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11306
  },
  {
    "item_id": 1010395119517,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11306
  },
  {
    "item_id": 1010395119518,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11306
  },
  {
    "item_id": 1010395119520,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11306
  },
  {
    "item_id": 1010395119523,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11306
  },
  {
    "item_id": 1010395119524,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11306
  },
  {
    "item_id": 1010395119525,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11306
  },
  {
    "item_id": 1010395119527,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11306
  },
  {
    "item_id": 1010395119529,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11306
  },
  {
    "item_id": 1010395119531,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11306
  },
  {
    "item_id": 1010395119534,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11306
  },
  {
    "item_id": 1010395119535,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11306
  },
  {
    "item_id": 1010627187716,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11304
  },
  {
    "item_id": 1010627187717,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11304
  },
  {
    "item_id": 1010627187720,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11304
  },
  {
    "item_id": 1010627187721,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11304
  },
  {
    "item_id": 1010627187723,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11304
  },
  {
    "item_id": 1010627187728,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11304
  },
  {
    "item_id": 1010627187729,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11304
  },
  {
    "item_id": 1010627187730,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11304
  },
  {
    "item_id": 1010627187735,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 280,
    "time_efficiency": 0,
    "type_id": 11304
  },
  {
    "item_id": 1010627187737,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11304
  },
  {
    "item_id": 1010627187741,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 292,
    "time_efficiency": 0,
    "type_id": 11304
  },
  {
    "item_id": 1010627187742,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11304
  },
  {
    "item_id": 1010627187746,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11304
  },
  {
    "item_id": 1010627187750,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11304
  },
  {
    "item_id": 1010627187752,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11304
  },
  {
    "item_id": 1010679316451,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 2047
  },
  {
    "item_id": 1010679316452,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 2047
  },
  {
    "item_id": 1010881151130,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1540
  },
  {
    "item_id": 1010881151132,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1540
  },
  {
    "item_id": 1010881151133,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1540
  },
  {
    "item_id": 1010881151135,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1540
  },
  {
    "item_id": 1011464995811,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1196
  },
  {
    "item_id": 1011464995812,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1196
  },
  {
    "item_id": 1011464995813,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1196
  },
  {
    "item_id": 1011464995814,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1196
  },
  {
    "item_id": 1011464995817,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1196
  },
  {
    "item_id": 1011464995853,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11306
  },
  {
    "item_id": 1011464995855,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11306
  },
  {
    "item_id": 1011464995857,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11306
  },
  {
    "item_id": 1011464995858,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11306
  },
  {
    "item_id": 1011464995859,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11306
  },
  {
    "item_id": 1011464995860,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11306
  },
  {
    "item_id": 1011464995861,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11306
  },
  {
    "item_id": 1011464995863,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11306
  },
  {
    "item_id": 1011464995864,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11306
  },
  {
    "item_id": 1011464995866,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11306
  },
  {
    "item_id": 1011464995868,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11306
  },
  {
    "item_id": 1011464995870,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11306
  },
  {
    "item_id": 1011464995871,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11306
  },
  {
    "item_id": 1011464995872,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11306
  },
  {
    "item_id": 1011464995873,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11306
  },
  {
    "item_id": 1011464995875,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11306
  },
  {
    "item_id": 1011464995877,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11306
  },
  {
    "item_id": 1011464995878,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11306
  },
  {
    "item_id": 1011464995879,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11306
  },
  {
    "item_id": 1011703411730,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1196
  },
  {
    "item_id": 1011703411731,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1196
  },
  {
    "item_id": 1011703411732,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1196
  },
  {
    "item_id": 1011703411733,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1196
  },
  {
    "item_id": 1011703411735,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1196
  },
  {
    "item_id": 1011703411737,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1196
  },
  {
    "item_id": 1011703411739,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1196
  },
  {
    "item_id": 1011703411741,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1196
  },
  {
    "item_id": 1011703411936,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 1540
  },
  {
    "item_id": 1011772708018,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 11613
  },
  {
    "item_id": 1011772708019,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 11613
  },
  {
    "item_id": 1011772708020,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 11613
  },
  {
    "item_id": 1011772708023,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 11613
  },
  {
    "item_id": 1011772708025,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 11613
  },
  {
    "item_id": 1011772708026,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 11613
  },
  {
    "item_id": 1011772708027,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 11613
  },
  {
    "item_id": 1011772708028,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 11613
  },
  {
    "item_id": 1011772708030,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 11613
  },
  {
    "item_id": 1011772708033,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 11613
  },
  {
    "item_id": 1011772708034,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 11613
  },
  {
    "item_id": 1011772708036,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 11613
  },
  {
    "item_id": 1011772708038,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 11613
  },
  {
    "item_id": 1011772708039,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 11613
  },
  {
    "item_id": 1011772708041,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 11613
  },
  {
    "item_id": 1011772708042,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 11613
  },
  {
    "item_id": 1011772708044,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 11613
  },
  {
    "item_id": 1011772708065,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1196
  },
  {
    "item_id": 1011772708067,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1196
  },
  {
    "item_id": 1011772708069,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1196
  },
  {
    "item_id": 1011772708070,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1196
  },
  {
    "item_id": 1011772708073,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1196
  },
  {
    "item_id": 1011772708075,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1196
  },
  {
    "item_id": 1011772708076,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1196
  },
  {
    "item_id": 1011772708077,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1196
  },
  {
    "item_id": 1011772708079,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1196
  },
  {
    "item_id": 1011772708083,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1196
  },
  {
    "item_id": 1011772708084,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1196
  },
  {
    "item_id": 1011772708086,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1196
  },
  {
    "item_id": 1011772708089,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1196
  },
  {
    "item_id": 1011772708091,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1196
  },
  {
    "item_id": 1011772708092,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1196
  },
  {
    "item_id": 1011772708095,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1196
  },
  {
    "item_id": 1011772708114,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 1540
  },
  {
    "item_id": 1011772708115,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 1540
  },
  {
    "item_id": 1011772708125,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 1540
  },
  {
    "item_id": 1011772708142,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 1540
  },
  {
    "item_id": 1011772708144,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 1540
  },
  {
    "item_id": 1011772708157,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 2047
  },
  {
    "item_id": 1011772708161,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 2047
  },
  {
    "item_id": 1011772708288,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 22543
  },
  {
    "item_id": 1011772708294,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 22543
  },
  {
    "item_id": 1011772708295,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 22543
  },
  {
    "item_id": 1011772708296,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 22543
  },
  {
    "item_id": 1011772708297,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 22543
  },
  {
    "item_id": 1011772708299,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 280,
    "time_efficiency": 18,
    "type_id": 22543
  },
  {
    "item_id": 1011772708302,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 22543
  },
  {
    "item_id": 1011772708304,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 22543
  },
  {
    "item_id": 1011816287970,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1129
  },
  {
    "item_id": 1011816287974,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1129
  },
  {
    "item_id": 1011816287996,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 11613
  },
  {
    "item_id": 1011816287997,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 11613
  },
  {
    "item_id": 1011816287998,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 11613
  },
  {
    "item_id": 1011816288004,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 11613
  },
  {
    "item_id": 1011816288006,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 297,
    "time_efficiency": 18,
    "type_id": 11613
  },
  {
    "item_id": 1011816288007,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 299,
    "time_efficiency": 18,
    "type_id": 11613
  },
  {
    "item_id": 1011816288008,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 11613
  },
  {
    "item_id": 1011816288009,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 11613
  },
  {
    "item_id": 1011816288011,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 11613
  },
  {
    "item_id": 1011846209823,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 22543
  },
  {
    "item_id": 1011846209825,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 22543
  },
  {
    "item_id": 1011846209836,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 22543
  },
  {
    "item_id": 1011846209840,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 22543
  },
  {
    "item_id": 1011861112782,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1540
  },
  {
    "item_id": 1011861112783,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 299,
    "time_efficiency": 0,
    "type_id": 1540
  },
  {
    "item_id": 1011861112784,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1540
  },
  {
    "item_id": 1011861112786,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1540
  },
  {
    "item_id": 1011861112789,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1540
  },
  {
    "item_id": 1011861112791,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1540
  },
  {
    "item_id": 1011861112793,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1540
  },
  {
    "item_id": 1011861112794,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1540
  },
  {
    "item_id": 1011861112795,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1540
  },
  {
    "item_id": 1011861112796,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1540
  },
  {
    "item_id": 1011861112797,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1540
  },
  {
    "item_id": 1011861112798,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 299,
    "time_efficiency": 0,
    "type_id": 1540
  },
  {
    "item_id": 1011861112800,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1540
  },
  {
    "item_id": 1011861112801,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1540
  },
  {
    "item_id": 1011861112802,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1540
  },
  {
    "item_id": 1011861112803,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1540
  },
  {
    "item_id": 1011861112804,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1540
  },
  {
    "item_id": 1011861112805,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1540
  },
  {
    "item_id": 1011861112806,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1540
  },
  {
    "item_id": 1011861112807,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 0,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1540
  },
  {
    "item_id": 1011960907069,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 2290
  },
  {
    "item_id": 1011960907070,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 2290
  },
  {
    "item_id": 1011960907071,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 2290
  },
  {
    "item_id": 1011960907076,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 2290
  },
  {
    "item_id": 1011960907106,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 50,
    "time_efficiency": 18,
    "type_id": 2604
  },
  {
    "item_id": 1011961005894,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11304
  },
  {
    "item_id": 1011961005896,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11304
  },
  {
    "item_id": 1011961005897,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11304
  },
  {
    "item_id": 1011961005899,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11304
  },
  {
    "item_id": 1011961005900,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11304
  },
  {
    "item_id": 1011988176897,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 2047
  },
  {
    "item_id": 1011988176898,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 2047
  },
  {
    "item_id": 1011988176899,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 2047
  },
  {
    "item_id": 1011988176900,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 2047
  },
  {
    "item_id": 1011988176911,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 2047
  },
  {
    "item_id": 1011988176912,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 2047
  },
  {
    "item_id": 1011988176913,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 2047
  },
  {
    "item_id": 1011988176916,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 2047
  },
  {
    "item_id": 1011988176917,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 2047
  },
  {
    "item_id": 1011988176918,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 2047
  },
  {
    "item_id": 1011988176919,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 2047
  },
  {
    "item_id": 1011988177005,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 287,
    "time_efficiency": 0,
    "type_id": 1245
  },
  {
    "item_id": 1011988177008,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1245
  },
  {
    "item_id": 1011988177011,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1245
  },
  {
    "item_id": 1011988177014,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 297,
    "time_efficiency": 0,
    "type_id": 1245
  },
  {
    "item_id": 1011988177018,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1245
  },
  {
    "item_id": 1011988177023,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 299,
    "time_efficiency": 0,
    "type_id": 1245
  },
  {
    "item_id": 1011988177024,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 299,
    "time_efficiency": 0,
    "type_id": 1245
  },
  {
    "item_id": 1011988177025,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1245
  },
  {
    "item_id": 1011988177028,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1245
  },
  {
    "item_id": 1011988177034,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1245
  },
  {
    "item_id": 1011988177040,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1245
  },
  {
    "item_id": 1011988177043,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1245
  },
  {
    "item_id": 1011994884319,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 100,
    "time_efficiency": 20,
    "type_id": 60341
  },
  {
    "item_id": 1012006529199,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 100,
    "time_efficiency": 20,
    "type_id": 60341
  },
  {
    "item_id": 1012006529239,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 100,
    "time_efficiency": 20,
    "type_id": 60341
  },
  {
    "item_id": 1012006529243,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 100,
    "time_efficiency": 20,
    "type_id": 60341
  },
  {
    "item_id": 1012006529249,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 100,
    "time_efficiency": 20,
    "type_id": 60341
  },
  {
    "item_id": 1012006529251,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 100,
    "time_efficiency": 20,
    "type_id": 60341
  },
  {
    "item_id": 1012006529255,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 100,
    "time_efficiency": 20,
    "type_id": 60341
  },
  {
    "item_id": 1012006529258,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 100,
    "time_efficiency": 20,
    "type_id": 60341
  },
  {
    "item_id": 1012006529260,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 100,
    "time_efficiency": 20,
    "type_id": 60341
  },
  {
    "item_id": 1012006529262,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 100,
    "time_efficiency": 20,
    "type_id": 60341
  },
  {
    "item_id": 1012006529265,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 100,
    "time_efficiency": 20,
    "type_id": 60341
  },
  {
    "item_id": 1012006529268,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 100,
    "time_efficiency": 20,
    "type_id": 60341
  },
  {
    "item_id": 1012006529270,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 100,
    "time_efficiency": 20,
    "type_id": 60341
  },
  {
    "item_id": 1012006529273,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 100,
    "time_efficiency": 20,
    "type_id": 60341
  },
  {
    "item_id": 1012006529275,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 100,
    "time_efficiency": 20,
    "type_id": 60341
  },
  {
    "item_id": 1012006529276,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 100,
    "time_efficiency": 20,
    "type_id": 60341
  },
  {
    "item_id": 1012006529280,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 100,
    "time_efficiency": 20,
    "type_id": 60341
  },
  {
    "item_id": 1012029708166,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 1318
  },
  {
    "item_id": 1012029708167,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 1318
  },
  {
    "item_id": 1012029708174,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 1318
  },
  {
    "item_id": 1012029708175,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 1318
  },
  {
    "item_id": 1012029708178,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 1318
  },
  {
    "item_id": 1012029708246,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 2292
  },
  {
    "item_id": 1012029708247,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 2292
  },
  {
    "item_id": 1012050741209,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1318
  },
  {
    "item_id": 1012050741210,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1318
  },
  {
    "item_id": 1012050741211,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1318
  },
  {
    "item_id": 1012050741213,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1318
  },
  {
    "item_id": 1012050741214,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1318
  },
  {
    "item_id": 1012082100594,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 2292
  },
  {
    "item_id": 1012082100595,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 2292
  },
  {
    "item_id": 1012082100596,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 2292
  },
  {
    "item_id": 1012082100597,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 2292
  },
  {
    "item_id": 1012082100598,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 2292
  },
  {
    "item_id": 1012082100600,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 2292
  },
  {
    "item_id": 1012082100608,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 2292
  },
  {
    "item_id": 1012082100610,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 2292
  },
  {
    "item_id": 1012082100611,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 2292
  },
  {
    "item_id": 1012082100612,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 2292
  },
  {
    "item_id": 1012082100613,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 2292
  },
  {
    "item_id": 1012082100614,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 2292
  },
  {
    "item_id": 1012111048546,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1318
  },
  {
    "item_id": 1012111048548,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1318
  },
  {
    "item_id": 1012111048551,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1318
  },
  {
    "item_id": 1012111048575,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1318
  },
  {
    "item_id": 1012111048578,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1318
  },
  {
    "item_id": 1012111048579,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1318
  },
  {
    "item_id": 1012111048581,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1318
  },
  {
    "item_id": 1012111048586,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1318
  },
  {
    "item_id": 1012111048587,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1318
  },
  {
    "item_id": 1012111048588,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1318
  },
  {
    "item_id": 1012111048591,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1318
  },
  {
    "item_id": 1012111048592,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1318
  },
  {
    "item_id": 1012111048593,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1318
  },
  {
    "item_id": 1012111048594,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1318
  },
  {
    "item_id": 1012111048595,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1318
  },
  {
    "item_id": 1012111048596,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1318
  },
  {
    "item_id": 1012111048597,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1318
  },
  {
    "item_id": 1012139393147,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 1540
  },
  {
    "item_id": 1012139393149,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 1540
  },
  {
    "item_id": 1012139393151,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 1540
  },
  {
    "item_id": 1012139393152,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 1540
  },
  {
    "item_id": 1012139393153,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 210,
    "time_efficiency": 18,
    "type_id": 1540
  },
  {
    "item_id": 1012139393154,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 1540
  },
  {
    "item_id": 1012139393155,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 1540
  },
  {
    "item_id": 1012139393158,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 1540
  },
  {
    "item_id": 1012139393160,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 1540
  },
  {
    "item_id": 1012139393161,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 1540
  },
  {
    "item_id": 1012139393162,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 1540
  },
  {
    "item_id": 1012139393163,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 1540
  },
  {
    "item_id": 1012139393164,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 1540
  },
  {
    "item_id": 1012139393166,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 1540
  },
  {
    "item_id": 1012139393167,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 1540
  },
  {
    "item_id": 1012139393169,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 1540
  },
  {
    "item_id": 1012139393210,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 2332
  },
  {
    "item_id": 1012139393213,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 2332
  },
  {
    "item_id": 1012139393214,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 2332
  },
  {
    "item_id": 1012139393217,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 2332
  },
  {
    "item_id": 1012139393218,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 2332
  },
  {
    "item_id": 1012139393219,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 2332
  },
  {
    "item_id": 1012139393220,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 2332
  },
  {
    "item_id": 1012139393224,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 2332
  },
  {
    "item_id": 1012139393225,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 2332
  },
  {
    "item_id": 1012139393226,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 2332
  },
  {
    "item_id": 1012142143495,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11278
  },
  {
    "item_id": 1012142143498,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11278
  },
  {
    "item_id": 1012142143501,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11278
  },
  {
    "item_id": 1012142143505,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11278
  },
  {
    "item_id": 1012142143511,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11278
  },
  {
    "item_id": 1012142143518,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11278
  },
  {
    "item_id": 1012142143525,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11278
  },
  {
    "item_id": 1012142143546,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11278
  },
  {
    "item_id": 1012142143549,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11278
  },
  {
    "item_id": 1012142143551,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11278
  },
  {
    "item_id": 1012142143555,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11278
  },
  {
    "item_id": 1012142143560,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11278
  },
  {
    "item_id": 1012166363416,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1245
  },
  {
    "item_id": 1012166363417,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1245
  },
  {
    "item_id": 1012166363420,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1245
  },
  {
    "item_id": 1012166363421,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1245
  },
  {
    "item_id": 1012166363423,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1245
  },
  {
    "item_id": 1012166363424,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1245
  },
  {
    "item_id": 1012166363429,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1245
  },
  {
    "item_id": 1012208988105,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1404
  },
  {
    "item_id": 1012208988106,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1404
  },
  {
    "item_id": 1012208988108,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1404
  },
  {
    "item_id": 1012208988110,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1404
  },
  {
    "item_id": 1012208988114,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 295,
    "time_efficiency": 0,
    "type_id": 1404
  },
  {
    "item_id": 1012208988115,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 297,
    "time_efficiency": 0,
    "type_id": 1404
  },
  {
    "item_id": 1012208988116,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 298,
    "time_efficiency": 0,
    "type_id": 1404
  },
  {
    "item_id": 1012208988118,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 297,
    "time_efficiency": 0,
    "type_id": 1404
  },
  {
    "item_id": 1012208988120,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 298,
    "time_efficiency": 0,
    "type_id": 1404
  },
  {
    "item_id": 1012208988122,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 299,
    "time_efficiency": 0,
    "type_id": 1404
  },
  {
    "item_id": 1012208988124,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 299,
    "time_efficiency": 0,
    "type_id": 1404
  },
  {
    "item_id": 1012208988125,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 298,
    "time_efficiency": 0,
    "type_id": 1404
  },
  {
    "item_id": 1012208988126,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 299,
    "time_efficiency": 0,
    "type_id": 1404
  },
  {
    "item_id": 1012211345736,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 298,
    "time_efficiency": 0,
    "type_id": 1404
  },
  {
    "item_id": 1012211345745,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 298,
    "time_efficiency": 0,
    "type_id": 1404
  },
  {
    "item_id": 1012211345746,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 298,
    "time_efficiency": 0,
    "type_id": 1404
  },
  {
    "item_id": 1012211345748,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 299,
    "time_efficiency": 0,
    "type_id": 1404
  },
  {
    "item_id": 1012211345754,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 299,
    "time_efficiency": 0,
    "type_id": 1404
  },
  {
    "item_id": 1012283194814,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 299,
    "time_efficiency": 0,
    "type_id": 1404
  },
  {
    "item_id": 1012283194816,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 299,
    "time_efficiency": 0,
    "type_id": 1404
  },
  {
    "item_id": 1012283194817,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 299,
    "time_efficiency": 0,
    "type_id": 1404
  },
  {
    "item_id": 1012283194821,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 2294
  },
  {
    "item_id": 1012283194822,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 2294
  },
  {
    "item_id": 1012283194823,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 2294
  },
  {
    "item_id": 1012283194824,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 2294
  },
  {
    "item_id": 1012283194837,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 2294
  },
  {
    "item_id": 1012283194839,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 2294
  },
  {
    "item_id": 1012283194840,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 2294
  },
  {
    "item_id": 1012283194841,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 2294
  },
  {
    "item_id": 1012283194899,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 290,
    "time_efficiency": 18,
    "type_id": 2604
  },
  {
    "item_id": 1012305536973,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 20,
    "type_id": 11358
  },
  {
    "item_id": 1012305536997,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 20,
    "type_id": 11358
  },
  {
    "item_id": 1012305537000,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 100,
    "time_efficiency": 20,
    "type_id": 11358
  },
  {
    "item_id": 1012451147677,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 296,
    "time_efficiency": 18,
    "type_id": 2604
  },
  {
    "item_id": 1012451147678,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 297,
    "time_efficiency": 18,
    "type_id": 2604
  },
  {
    "item_id": 1012451147679,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 299,
    "time_efficiency": 18,
    "type_id": 2604
  },
  {
    "item_id": 1012451147683,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 299,
    "time_efficiency": 18,
    "type_id": 2604
  },
  {
    "item_id": 1012451147684,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 2604
  },
  {
    "item_id": 1012451147686,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 2604
  },
  {
    "item_id": 1012451147687,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 2604
  },
  {
    "item_id": 1012451147688,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 2604
  },
  {
    "item_id": 1012451147690,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 2604
  },
  {
    "item_id": 1012451147691,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 2604
  },
  {
    "item_id": 1012451147692,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 2604
  },
  {
    "item_id": 1012451147693,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 2604
  },
  {
    "item_id": 1012451147695,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 2604
  },
  {
    "item_id": 1012451147696,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 2604
  },
  {
    "item_id": 1012451147698,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 2604
  },
  {
    "item_id": 1012451147700,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 2604
  },
  {
    "item_id": 1012451147735,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 299,
    "time_efficiency": 0,
    "type_id": 1404
  },
  {
    "item_id": 1012451147737,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1404
  },
  {
    "item_id": 1012451147739,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1404
  },
  {
    "item_id": 1012451147742,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1404
  },
  {
    "item_id": 1012451147775,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1404
  },
  {
    "item_id": 1012451147787,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 299,
    "time_efficiency": 20,
    "type_id": 11358
  },
  {
    "item_id": 1012451147789,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 200,
    "time_efficiency": 20,
    "type_id": 11358
  },
  {
    "item_id": 1012451147790,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 20,
    "type_id": 11358
  },
  {
    "item_id": 1012451147799,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 20,
    "type_id": 11358
  },
  {
    "item_id": 1012451147810,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 20,
    "type_id": 11358
  },
  {
    "item_id": 1012451147812,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 20,
    "type_id": 11358
  },
  {
    "item_id": 1012451147900,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 20,
    "type_id": 11358
  },
  {
    "item_id": 1012451147926,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 100,
    "time_efficiency": 20,
    "type_id": 60341
  },
  {
    "item_id": 1012451147927,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 100,
    "time_efficiency": 20,
    "type_id": 60341
  },
  {
    "item_id": 1012451188994,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11358
  },
  {
    "item_id": 1012451188999,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11358
  },
  {
    "item_id": 1012451189014,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 298,
    "time_efficiency": 0,
    "type_id": 11358
  },
  {
    "item_id": 1012451189016,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 299,
    "time_efficiency": 0,
    "type_id": 11358
  },
  {
    "item_id": 1012451189020,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11358
  },
  {
    "item_id": 1012451189023,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 11358
  },
  {
    "item_id": 1012451189123,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 20,
    "type_id": 11358
  },
  {
    "item_id": 1012451189125,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 20,
    "type_id": 11358
  },
  {
    "item_id": 1012451189131,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 20,
    "type_id": 11358
  },
  {
    "item_id": 1012451189132,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 20,
    "type_id": 11358
  },
  {
    "item_id": 1012451189134,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 20,
    "type_id": 11358
  },
  {
    "item_id": 1012512986383,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1404
  },
  {
    "item_id": 1012512986384,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1404
  },
  {
    "item_id": 1012512986386,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1404
  },
  {
    "item_id": 1012512986387,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1404
  },
  {
    "item_id": 1012512986388,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1404
  },
  {
    "item_id": 1012512986395,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1404
  },
  {
    "item_id": 1012512986397,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1404
  },
  {
    "item_id": 1012512986404,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1404
  },
  {
    "item_id": 1012512986409,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1404
  },
  {
    "item_id": 1012512986413,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1404
  },
  {
    "item_id": 1012512986415,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1404
  },
  {
    "item_id": 1012512986441,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 20,
    "type_id": 11358
  },
  {
    "item_id": 1012512986442,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 20,
    "type_id": 11358
  },
  {
    "item_id": 1012512986443,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 20,
    "type_id": 11358
  },
  {
    "item_id": 1012512986444,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 20,
    "type_id": 11358
  },
  {
    "item_id": 1012512986445,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 20,
    "type_id": 11358
  },
  {
    "item_id": 1012512986446,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 299,
    "time_efficiency": 20,
    "type_id": 11358
  },
  {
    "item_id": 1012512986447,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 20,
    "type_id": 11358
  },
  {
    "item_id": 1012512986448,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 20,
    "type_id": 11358
  },
  {
    "item_id": 1012512986449,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 20,
    "type_id": 11358
  },
  {
    "item_id": 1012512986450,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 20,
    "type_id": 11358
  },
  {
    "item_id": 1012512986508,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 20,
    "type_id": 2294
  },
  {
    "item_id": 1012512986509,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 20,
    "type_id": 2294
  },
  {
    "item_id": 1012512986510,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 20,
    "type_id": 2294
  },
  {
    "item_id": 1012512986511,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 20,
    "type_id": 2294
  },
  {
    "item_id": 1012512986517,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 20,
    "type_id": 2294
  },
  {
    "item_id": 1012512986518,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 20,
    "type_id": 2294
  },
  {
    "item_id": 1012512986519,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 20,
    "type_id": 2294
  },
  {
    "item_id": 1012512986520,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 20,
    "type_id": 2294
  },
  {
    "item_id": 1012524445260,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 20,
    "type_id": 828
  },
  {
    "item_id": 1012524445263,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 20,
    "type_id": 828
  },
  {
    "item_id": 1012524445266,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 20,
    "type_id": 828
  },
  {
    "item_id": 1012524445267,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 20,
    "type_id": 828
  },
  {
    "item_id": 1012524445270,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 20,
    "type_id": 828
  },
  {
    "item_id": 1012524445308,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 20,
    "type_id": 828
  },
  {
    "item_id": 1012524445309,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 20,
    "type_id": 828
  },
  {
    "item_id": 1012524445311,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 20,
    "type_id": 828
  },
  {
    "item_id": 1012524445346,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 20,
    "type_id": 11358
  },
  {
    "item_id": 1012524445347,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 20,
    "type_id": 11358
  },
  {
    "item_id": 1012524445348,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 20,
    "type_id": 11358
  },
  {
    "item_id": 1012524445548,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 12275
  },
  {
    "item_id": 1012524447936,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 249,
    "time_efficiency": 0,
    "type_id": 9945
  },
  {
    "item_id": 1012524447942,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 299,
    "time_efficiency": 0,
    "type_id": 9945
  },
  {
    "item_id": 1012524447951,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 299,
    "time_efficiency": 0,
    "type_id": 9945
  },
  {
    "item_id": 1012524447952,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 299,
    "time_efficiency": 0,
    "type_id": 9945
  },
  {
    "item_id": 1012524447958,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 9945
  },
  {
    "item_id": 1012524447960,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 9945
  },
  {
    "item_id": 1012524447961,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 9945
  },
  {
    "item_id": 1012524447962,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 9945
  },
  {
    "item_id": 1012524447963,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 9945
  },
  {
    "item_id": 1012524447989,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1446
  },
  {
    "item_id": 1012524447995,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1446
  },
  {
    "item_id": 1012524447998,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1446
  },
  {
    "item_id": 1012524448004,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1446
  },
  {
    "item_id": 1012524448010,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1446
  },
  {
    "item_id": 1012524448011,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 166,
    "time_efficiency": 0,
    "type_id": 1446
  },
  {
    "item_id": 1012524448012,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 233,
    "time_efficiency": 0,
    "type_id": 1446
  },
  {
    "item_id": 1012524448015,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1446
  },
  {
    "item_id": 1012524448018,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1446
  },
  {
    "item_id": 1012565456052,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 2290
  },
  {
    "item_id": 1012565456077,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 2290
  },
  {
    "item_id": 1012565456080,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 2290
  },
  {
    "item_id": 1012565456081,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 2290
  },
  {
    "item_id": 1012565456181,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 2604
  },
  {
    "item_id": 1012565456189,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 2604
  },
  {
    "item_id": 1012565456193,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 2604
  },
  {
    "item_id": 1012565456194,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 2604
  },
  {
    "item_id": 1012565456196,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 2604
  },
  {
    "item_id": 1012565456197,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 2604
  },
  {
    "item_id": 1012565456199,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 2604
  },
  {
    "item_id": 1012565456203,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 2604
  },
  {
    "item_id": 1012565456204,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 2604
  },
  {
    "item_id": 1012565456205,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 2604
  },
  {
    "item_id": 1012565456206,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 2604
  },
  {
    "item_id": 1012569515657,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 20,
    "type_id": 828
  },
  {
    "item_id": 1012569515658,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 20,
    "type_id": 828
  },
  {
    "item_id": 1012569515659,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 290,
    "time_efficiency": 20,
    "type_id": 828
  },
  {
    "item_id": 1012569515661,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 20,
    "type_id": 828
  },
  {
    "item_id": 1012569515664,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 20,
    "type_id": 828
  },
  {
    "item_id": 1012569515665,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 20,
    "type_id": 828
  },
  {
    "item_id": 1012569515669,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 20,
    "type_id": 828
  },
  {
    "item_id": 1012569515671,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 20,
    "type_id": 828
  },
  {
    "item_id": 1012569515673,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 20,
    "type_id": 828
  },
  {
    "item_id": 1012569515674,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 20,
    "type_id": 828
  },
  {
    "item_id": 1012569515675,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 20,
    "type_id": 828
  },
  {
    "item_id": 1012569515677,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 20,
    "type_id": 828
  },
  {
    "item_id": 1012569515678,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 20,
    "type_id": 828
  },
  {
    "item_id": 1012569515679,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 20,
    "type_id": 828
  },
  {
    "item_id": 1012588511551,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1404
  },
  {
    "item_id": 1012588511568,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1404
  },
  {
    "item_id": 1012588511569,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1404
  },
  {
    "item_id": 1012588511570,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1404
  },
  {
    "item_id": 1012588511573,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1404
  },
  {
    "item_id": 1012588511574,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1404
  },
  {
    "item_id": 1012588511575,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1404
  },
  {
    "item_id": 1012598795592,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 9945
  },
  {
    "item_id": 1012598795595,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 9945
  },
  {
    "item_id": 1012598795600,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 9945
  },
  {
    "item_id": 1012598795602,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 9945
  },
  {
    "item_id": 1012598795603,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 9945
  },
  {
    "item_id": 1012598795605,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 9945
  },
  {
    "item_id": 1012598795606,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 9945
  },
  {
    "item_id": 1012598795608,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 9945
  },
  {
    "item_id": 1012598795609,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 9945
  },
  {
    "item_id": 1012598795610,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 9945
  },
  {
    "item_id": 1012598795611,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 9945
  },
  {
    "item_id": 1012598795615,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 9945
  },
  {
    "item_id": 1012627069345,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 12275
  },
  {
    "item_id": 1012627069348,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 12275
  },
  {
    "item_id": 1012627069349,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 12275
  },
  {
    "item_id": 1012627069350,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 12275
  },
  {
    "item_id": 1012627069351,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 12275
  },
  {
    "item_id": 1012627069352,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 12275
  },
  {
    "item_id": 1012627069353,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 12275
  },
  {
    "item_id": 1012629890780,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 2604
  },
  {
    "item_id": 1012629890782,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 2604
  },
  {
    "item_id": 1012629890786,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 2604
  },
  {
    "item_id": 1012629890787,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 2604
  },
  {
    "item_id": 1012629890788,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 2604
  },
  {
    "item_id": 1012629890790,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 2604
  },
  {
    "item_id": 1012629890791,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 2604
  },
  {
    "item_id": 1012629890797,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 2604
  },
  {
    "item_id": 1012629890798,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 2604
  },
  {
    "item_id": 1012654676537,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 11613
  },
  {
    "item_id": 1012654676543,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 11613
  },
  {
    "item_id": 1012654676544,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 11613
  },
  {
    "item_id": 1012654676547,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 11613
  },
  {
    "item_id": 1012654676548,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 11613
  },
  {
    "item_id": 1012654676551,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 11613
  },
  {
    "item_id": 1012654676553,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 11613
  },
  {
    "item_id": 1012654676555,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 11613
  },
  {
    "item_id": 1012654676558,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 11613
  },
  {
    "item_id": 1012654676559,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 11613
  },
  {
    "item_id": 1012654676562,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 11613
  },
  {
    "item_id": 1012654676563,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 11613
  },
  {
    "item_id": 1012654676564,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 11613
  },
  {
    "item_id": 1012654676565,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 11613
  },
  {
    "item_id": 1012654676567,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 11613
  },
  {
    "item_id": 1012654676570,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 11613
  },
  {
    "item_id": 1012654676572,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 11613
  },
  {
    "item_id": 1012673323776,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 299,
    "time_efficiency": 0,
    "type_id": 3840
  },
  {
    "item_id": 1012673323811,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 3840
  },
  {
    "item_id": 1012694074153,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1404
  },
  {
    "item_id": 1012694074154,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1404
  },
  {
    "item_id": 1012694074156,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1404
  },
  {
    "item_id": 1012694074157,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1404
  },
  {
    "item_id": 1012694074159,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1404
  },
  {
    "item_id": 1012694074160,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1404
  },
  {
    "item_id": 1012694074161,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1404
  },
  {
    "item_id": 1012694074163,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1404
  },
  {
    "item_id": 1012694074164,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1404
  },
  {
    "item_id": 1012694074165,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1404
  },
  {
    "item_id": 1012694074166,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1404
  },
  {
    "item_id": 1012694074167,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1404
  },
  {
    "item_id": 1012694074168,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1404
  },
  {
    "item_id": 1012694074169,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1404
  },
  {
    "item_id": 1012694074170,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1404
  },
  {
    "item_id": 1012694074171,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1404
  },
  {
    "item_id": 1012694074172,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1404
  },
  {
    "item_id": 1012694074173,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1404
  },
  {
    "item_id": 1012694074174,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1404
  },
  {
    "item_id": 1012694074175,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1404
  },
  {
    "item_id": 1012694460777,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 2604
  },
  {
    "item_id": 1012694460789,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 2604
  },
  {
    "item_id": 1012694460791,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 2604
  },
  {
    "item_id": 1012694460799,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 2604
  },
  {
    "item_id": 1012694460800,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 200,
    "time_efficiency": 18,
    "type_id": 2604
  },
  {
    "item_id": 1012694460830,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1245
  },
  {
    "item_id": 1012694460836,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1245
  },
  {
    "item_id": 1012694460838,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1245
  },
  {
    "item_id": 1012694460841,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1245
  },
  {
    "item_id": 1012694460842,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1245
  },
  {
    "item_id": 1012694460843,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1245
  },
  {
    "item_id": 1012705384862,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 12275
  },
  {
    "item_id": 1012705384863,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 12275
  },
  {
    "item_id": 1012705384864,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 12275
  },
  {
    "item_id": 1012705384866,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 12275
  },
  {
    "item_id": 1012749004459,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1446
  },
  {
    "item_id": 1012749004464,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1446
  },
  {
    "item_id": 1012749004465,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1446
  },
  {
    "item_id": 1012749004467,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1446
  },
  {
    "item_id": 1012749004468,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 273,
    "time_efficiency": 0,
    "type_id": 1446
  },
  {
    "item_id": 1012749004470,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1446
  },
  {
    "item_id": 1012749004471,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1446
  },
  {
    "item_id": 1012749004478,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1446
  },
  {
    "item_id": 1012749004479,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1446
  },
  {
    "item_id": 1012770617682,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1404
  },
  {
    "item_id": 1012770617684,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1404
  },
  {
    "item_id": 1012770617685,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1404
  },
  {
    "item_id": 1012770617687,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1404
  },
  {
    "item_id": 1012770617689,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1404
  },
  {
    "item_id": 1012770617691,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1404
  },
  {
    "item_id": 1012770617693,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1404
  },
  {
    "item_id": 1012770617695,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1404
  },
  {
    "item_id": 1012770617696,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1404
  },
  {
    "item_id": 1012770617697,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1404
  },
  {
    "item_id": 1012770617698,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1404
  },
  {
    "item_id": 1012770617699,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1404
  },
  {
    "item_id": 1012770617700,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1404
  },
  {
    "item_id": 1012770617701,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1404
  },
  {
    "item_id": 1012770617702,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1404
  },
  {
    "item_id": 1012770617703,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1404
  },
  {
    "item_id": 1012770617704,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1404
  },
  {
    "item_id": 1012770617705,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1404
  },
  {
    "item_id": 1012770617707,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1404
  },
  {
    "item_id": 1012770617708,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1404
  },
  {
    "item_id": 1012770652256,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1245
  },
  {
    "item_id": 1012770652257,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1245
  },
  {
    "item_id": 1012770652258,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1245
  },
  {
    "item_id": 1012770652259,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1245
  },
  {
    "item_id": 1012770652260,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1245
  },
  {
    "item_id": 1012770652261,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1245
  },
  {
    "item_id": 1012770652262,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1245
  },
  {
    "item_id": 1012770652263,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1245
  },
  {
    "item_id": 1012770652264,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1245
  },
  {
    "item_id": 1012770652266,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1245
  },
  {
    "item_id": 1012770652267,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1245
  },
  {
    "item_id": 1012770652268,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1245
  },
  {
    "item_id": 1012770652270,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1245
  },
  {
    "item_id": 1012770652271,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1245
  },
  {
    "item_id": 1012770652273,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1245
  },
  {
    "item_id": 1012770652275,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1245
  },
  {
    "item_id": 1012770652277,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1245
  },
  {
    "item_id": 1012770652279,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1245
  },
  {
    "item_id": 1012770652280,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1245
  },
  {
    "item_id": 1012770652281,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1245
  },
  {
    "item_id": 1012770652310,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 2604
  },
  {
    "item_id": 1012770652311,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 2604
  },
  {
    "item_id": 1012770652312,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 2604
  },
  {
    "item_id": 1012770652313,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 2604
  },
  {
    "item_id": 1012770652315,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 2604
  },
  {
    "item_id": 1012770652316,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 2604
  },
  {
    "item_id": 1012770652317,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 2604
  },
  {
    "item_id": 1012770652318,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 2604
  },
  {
    "item_id": 1012770652319,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 2604
  },
  {
    "item_id": 1012770652320,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 2604
  },
  {
    "item_id": 1012770652321,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 2604
  },
  {
    "item_id": 1012770652324,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 2604
  },
  {
    "item_id": 1012770652325,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 2604
  },
  {
    "item_id": 1012770652326,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 2604
  },
  {
    "item_id": 1012770652327,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 2604
  },
  {
    "item_id": 1012770652328,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 2604
  },
  {
    "item_id": 1012770652329,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 2604
  },
  {
    "item_id": 1012770652331,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 2604
  },
  {
    "item_id": 1012770652333,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 2604
  },
  {
    "item_id": 1012770652334,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 2604
  },
  {
    "item_id": 1012770652380,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 20,
    "type_id": 1318
  },
  {
    "item_id": 1012770652381,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 20,
    "type_id": 1318
  },
  {
    "item_id": 1012770652382,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 20,
    "type_id": 1318
  },
  {
    "item_id": 1012770652383,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 20,
    "type_id": 1318
  },
  {
    "item_id": 1012770652385,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 20,
    "type_id": 1318
  },
  {
    "item_id": 1012770652386,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 20,
    "type_id": 1318
  },
  {
    "item_id": 1012770652387,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 20,
    "type_id": 1318
  },
  {
    "item_id": 1012770652388,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 20,
    "type_id": 1318
  },
  {
    "item_id": 1012770652391,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 20,
    "type_id": 1318
  },
  {
    "item_id": 1012770652393,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 20,
    "type_id": 1318
  },
  {
    "item_id": 1012770652394,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 20,
    "type_id": 1318
  },
  {
    "item_id": 1012770652396,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 20,
    "type_id": 1318
  },
  {
    "item_id": 1015033991561,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 2332
  },
  {
    "item_id": 1015033991564,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 2332
  },
  {
    "item_id": 1015033991565,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 2332
  },
  {
    "item_id": 1015033991568,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 2332
  },
  {
    "item_id": 1015033991572,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 2332
  },
  {
    "item_id": 1015046061522,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 11613
  },
  {
    "item_id": 1015046061523,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 11613
  },
  {
    "item_id": 1015046061524,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 11613
  },
  {
    "item_id": 1015046061526,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 11613
  },
  {
    "item_id": 1015046061530,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 11613
  },
  {
    "item_id": 1015046061536,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 11613
  },
  {
    "item_id": 1015046061537,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 11613
  },
  {
    "item_id": 1015046061538,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 11613
  },
  {
    "item_id": 1015046061540,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 11613
  },
  {
    "item_id": 1015046061542,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 11613
  },
  {
    "item_id": 1015046088114,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 2604
  },
  {
    "item_id": 1015046088116,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 2604
  },
  {
    "item_id": 1015046088118,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 2604
  },
  {
    "item_id": 1015046088119,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 2604
  },
  {
    "item_id": 1015046088139,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 2604
  },
  {
    "item_id": 1015046088141,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 2604
  },
  {
    "item_id": 1015046088145,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 2604
  },
  {
    "item_id": 1015046088147,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 2604
  },
  {
    "item_id": 1015046088148,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 2604
  },
  {
    "item_id": 1015046088149,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 18,
    "type_id": 2604
  },
  {
    "item_id": 1015046088176,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1245
  },
  {
    "item_id": 1015046088182,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1245
  },
  {
    "item_id": 1015046088187,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1245
  },
  {
    "item_id": 1015046088190,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1245
  },
  {
    "item_id": 1015046088192,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1245
  },
  {
    "item_id": 1015046088195,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1245
  },
  {
    "item_id": 1015046088198,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1245
  },
  {
    "item_id": 1015046088199,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1245
  },
  {
    "item_id": 1015046088201,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1245
  },
  {
    "item_id": 1015046088204,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1245
  },
  {
    "item_id": 1015046088205,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1245
  },
  {
    "item_id": 1015046088209,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1245
  },
  {
    "item_id": 1015046088211,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1245
  },
  {
    "item_id": 1015046088216,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1245
  },
  {
    "item_id": 1015046088217,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1245
  },
  {
    "item_id": 1015046088265,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1245
  },
  {
    "item_id": 1015046121335,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1404
  },
  {
    "item_id": 1015046121337,
    "location_flag": "Hangar",
    "location_id": 60006907,
    "material_efficiency": 10,
    "quantity": -2,
    "runs": 300,
    "time_efficiency": 0,
    "type_id": 1404
  }
]
