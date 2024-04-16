# Mining Operations with mandarory headers
curl --request GET --location "http://localhost:3000/nin/v1/character/miningoperations" \
--header 'Content-Type: application/json' \
--cookie 'Authentication=Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6IkpXVC1TaWduYXR1cmUtS2V5IiwidHlwIjoiSldUIn0.eyJzY3AiOlsiZXNpLWFzc2V0cy5yZWFkX2Fzc2V0cy52MSIsImVzaS1pbmR1c3RyeS5yZWFkX2NoYXJhY3Rlcl9taW5pbmcudjEiXSwianRpIjoiY2NhZWFmNjctNjM2ZS00ZDhhLThiNDQtZThlNzViM2ExMjE2Iiwia2lkIjoiSldULVNpZ25hdHVyZS1LZXkiLCJzdWIiOiJDSEFSQUNURVI6RVZFOjkzODEzMzEwIiwiYXpwIjoiNjgzMDg0YWI1Zjg4NDhkNGIxODc0NjJhYzNiOTc2NzciLCJ0ZW5hbnQiOiJ0cmFucXVpbGl0eSIsInRpZXIiOiJsaXZlIiwicmVnaW9uIjoid29ybGQiLCJhdWQiOlsiNjgzMDg0YWI1Zjg4NDhkNGIxODc0NjJhYzNiOTc2NzciLCJFVkUgT25saW5lIl0sIm5hbWUiOiJQZXJpY28gVHVlcnRvIiwib3duZXIiOiJRc2lrT2pXUFFERnAzM1hucEl1VzhnM0Z5eFE9IiwiZXhwIjoxNzExNTMwMjExLCJpYXQiOjE3MTE1MjkwMTEsImlzcyI6Imh0dHBzOi8vbG9naW4uZXZlb25saW5lLmNvbSJ9.cVkBkCgLir-kzahfqxjhjNMmQoks1xbt0zthSvWt0Ynuv-rJhI25m4SGReMJSvnjyseh9bmyblJbXOYEJeF_zdbuyP-KRwshWj4hre-VZ4jJPf4Rl-QcdRxPJ-2hPk-w06ltuDCwWUmaCedQauXg9tHKnM8KGapZ64OENaEKbY4A4ilAS0Iukaz9HqqXEuW7rcGAKXvN27yguF2U_hoN3QzCzGcOB0sLyiW1lpjpOC-vO-1X9nc-RUJGK4bYxoVMtxUL1bBrbBmpp2Rb1A43bpsgNGWQQc-PSzasWw2sNX90oYFHKCGQ7_dRCsk2cT7xgcdaAGjVZe9yaIHTg4Ogzg'

# Mining Operations without Authentication he
curl --request GET --location "http://localhost:3000/nin/v1/character/123/miningoperations"

# Stage Checks
# Mining Operations from ESI
curl -X GET "https://esi.evetech.net/latest/characters/93813310/mining/?datasource=tranquility&page=1" -H "accept: application/json" -H "authorization: Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6IkpXVC1TaWduYXR1cmUtS2V5IiwidHlwIjoiSldUIn0.eyJzY3AiOlsiZXNpLWFzc2V0cy5yZWFkX2Fzc2V0cy52MSIsImVzaS1jb250cmFjdHMucmVhZF9jb3Jwb3JhdGlvbl9jb250cmFjdHMudjEiLCJlc2ktaW5kdXN0cnkucmVhZF9jaGFyYWN0ZXJfbWluaW5nLnYxIl0sImp0aSI6IjRlN2RiYjM0LTI2ZWQtNDJmZS1hM2E5LWRiOWRmZTgyYzEzMCIsImtpZCI6IkpXVC1TaWduYXR1cmUtS2V5Iiwic3ViIjoiQ0hBUkFDVEVSOkVWRTo5MzgxMzMxMCIsImF6cCI6IjY4MzA4NGFiNWY4ODQ4ZDRiMTg3NDYyYWMzYjk3Njc3IiwidGVuYW50IjoidHJhbnF1aWxpdHkiLCJ0aWVyIjoibGl2ZSIsInJlZ2lvbiI6IndvcmxkIiwiYXVkIjpbIjY4MzA4NGFiNWY4ODQ4ZDRiMTg3NDYyYWMzYjk3Njc3IiwiRVZFIE9ubGluZSJdLCJuYW1lIjoiUGVyaWNvIFR1ZXJ0byIsIm93bmVyIjoiUXNpa09qV1BRREZwMzNYbnBJdVc4ZzNGeXhRPSIsImV4cCI6MTcxMjE1MTg4NywiaWF0IjoxNzEyMTUwNjg3LCJpc3MiOiJodHRwczovL2xvZ2luLmV2ZW9ubGluZS5jb20ifQ.bjqdvzXuUUUJzuNkzelpSeykPI3U-qXsGqIYLJM0av1k3S3HK4tl_svtwxYXktAS9CRB1uYc9906x524zBkPk6msMIV8iob7-NveZtelm_gVrycK6t_Sov6uyxyja8hkgRPVpcypluWUtXlIiiYJKX1-GSTKVXyIB0F9hvXZKDLGdohl_4eRLA0Vl2GWfVWzAmCFZuD75lZoacJr9TTJSbr_569D8PsUSj-CHJh6JffmuTbyW0aiMRY2BjBBTu0DwM5AJyhxUATN_1aKI-44I7d0bTEnIuYIUt2xPhffyORaTJokundchebcZXWvVjYnHEX4VBAbZa4hOl3lSVZ5Ng" -H "Cache-Control: no-cache"

## Mining operations from NEST
curl --request GET --location "http://localhost:5213/nin/v1/character/miningoperations" \
--header 'Content-Type: application/json' \
--header "accept: application/json" \
--header 'Cache-Control: no-cache' \
--cookie 'Authentication=Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6IkpXVC1TaWduYXR1cmUtS2V5IiwidHlwIjoiSldUIn0.eyJzY3AiOlsiZXNpLWFzc2V0cy5yZWFkX2Fzc2V0cy52MSIsImVzaS1jb250cmFjdHMucmVhZF9jb3Jwb3JhdGlvbl9jb250cmFjdHMudjEiLCJlc2ktaW5kdXN0cnkucmVhZF9jaGFyYWN0ZXJfbWluaW5nLnYxIl0sImp0aSI6IjRlN2RiYjM0LTI2ZWQtNDJmZS1hM2E5LWRiOWRmZTgyYzEzMCIsImtpZCI6IkpXVC1TaWduYXR1cmUtS2V5Iiwic3ViIjoiQ0hBUkFDVEVSOkVWRTo5MzgxMzMxMCIsImF6cCI6IjY4MzA4NGFiNWY4ODQ4ZDRiMTg3NDYyYWMzYjk3Njc3IiwidGVuYW50IjoidHJhbnF1aWxpdHkiLCJ0aWVyIjoibGl2ZSIsInJlZ2lvbiI6IndvcmxkIiwiYXVkIjpbIjY4MzA4NGFiNWY4ODQ4ZDRiMTg3NDYyYWMzYjk3Njc3IiwiRVZFIE9ubGluZSJdLCJuYW1lIjoiUGVyaWNvIFR1ZXJ0byIsIm93bmVyIjoiUXNpa09qV1BRREZwMzNYbnBJdVc4ZzNGeXhRPSIsImV4cCI6MTcxMjE1MTg4NywiaWF0IjoxNzEyMTUwNjg3LCJpc3MiOiJodHRwczovL2xvZ2luLmV2ZW9ubGluZS5jb20ifQ.bjqdvzXuUUUJzuNkzelpSeykPI3U-qXsGqIYLJM0av1k3S3HK4tl_svtwxYXktAS9CRB1uYc9906x524zBkPk6msMIV8iob7-NveZtelm_gVrycK6t_Sov6uyxyja8hkgRPVpcypluWUtXlIiiYJKX1-GSTKVXyIB0F9hvXZKDLGdohl_4eRLA0Vl2GWfVWzAmCFZuD75lZoacJr9TTJSbr_569D8PsUSj-CHJh6JffmuTbyW0aiMRY2BjBBTu0DwM5AJyhxUATN_1aKI-44I7d0bTEnIuYIUt2xPhffyORaTJokundchebcZXWvVjYnHEX4VBAbZa4hOl3lSVZ5Ng'


curl --request GET --location "http://localhost:30000/nin/v1/character/miningoperations" \
--header 'Content-Type: application/json' \
--header "accept: application/json" \
--header 'Cache-Control: no-cache' \
--cookie 'Authentication=Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6IkpXVC1TaWduYXR1cmUtS2V5IiwidHlwIjoiSldUIn0.eyJzY3AiOlsiZXNpLWFzc2V0cy5yZWFkX2Fzc2V0cy52MSIsImVzaS1jb250cmFjdHMucmVhZF9jb3Jwb3JhdGlvbl9jb250cmFjdHMudjEiLCJlc2ktaW5kdXN0cnkucmVhZF9jaGFyYWN0ZXJfbWluaW5nLnYxIl0sImp0aSI6IjRlN2RiYjM0LTI2ZWQtNDJmZS1hM2E5LWRiOWRmZTgyYzEzMCIsImtpZCI6IkpXVC1TaWduYXR1cmUtS2V5Iiwic3ViIjoiQ0hBUkFDVEVSOkVWRTo5MzgxMzMxMCIsImF6cCI6IjY4MzA4NGFiNWY4ODQ4ZDRiMTg3NDYyYWMzYjk3Njc3IiwidGVuYW50IjoidHJhbnF1aWxpdHkiLCJ0aWVyIjoibGl2ZSIsInJlZ2lvbiI6IndvcmxkIiwiYXVkIjpbIjY4MzA4NGFiNWY4ODQ4ZDRiMTg3NDYyYWMzYjk3Njc3IiwiRVZFIE9ubGluZSJdLCJuYW1lIjoiUGVyaWNvIFR1ZXJ0byIsIm93bmVyIjoiUXNpa09qV1BRREZwMzNYbnBJdVc4ZzNGeXhRPSIsImV4cCI6MTcxMjE1MTg4NywiaWF0IjoxNzEyMTUwNjg3LCJpc3MiOiJodHRwczovL2xvZ2luLmV2ZW9ubGluZS5jb20ifQ.bjqdvzXuUUUJzuNkzelpSeykPI3U-qXsGqIYLJM0av1k3S3HK4tl_svtwxYXktAS9CRB1uYc9906x524zBkPk6msMIV8iob7-NveZtelm_gVrycK6t_Sov6uyxyja8hkgRPVpcypluWUtXlIiiYJKX1-GSTKVXyIB0F9hvXZKDLGdohl_4eRLA0Vl2GWfVWzAmCFZuD75lZoacJr9TTJSbr_569D8PsUSj-CHJh6JffmuTbyW0aiMRY2BjBBTu0DwM5AJyhxUATN_1aKI-44I7d0bTEnIuYIUt2xPhffyORaTJokundchebcZXWvVjYnHEX4VBAbZa4hOl3lSVZ5Ng'

# Commands to retrieve FuzzWorks data
curl --location "https://market.fuzzwork.co.uk/aggregates/?region=30000142&types=34,35,36,37,38,39,40"

# Command to get one Esi Type data
curl --request GET --location "http://localhost:3000/esi/v1/universe/types/17464"
