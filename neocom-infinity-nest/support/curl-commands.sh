# Mining Operations with mandarory headers
curl --request GET --location "http://localhost:3000/nin/v1/character/123/miningoperations" \
--header 'Content-Type: application/json' \
--cookie 'Authentication=Bearer ffgg'

# Mining Operations without Authentication he
curl --request GET --location "http://localhost:3000/nin/v1/character/123/miningoperations"
