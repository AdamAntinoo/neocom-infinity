# Mining Operations with mandarory headers
curl --request GET --location "http://localhost:3000/nin/v1/character/93813310/miningoperations" \
--header 'Content-Type: application/json' \
--cookie 'Authentication=Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6IkpXVC1TaWduYXR1cmUtS2V5IiwidHlwIjoiSldUIn0.eyJzY3AiOlsiZXNpLWFzc2V0cy5yZWFkX2Fzc2V0cy52MSIsImVzaS1pbmR1c3RyeS5yZWFkX2NoYXJhY3Rlcl9taW5pbmcudjEiXSwianRpIjoiY2NhZWFmNjctNjM2ZS00ZDhhLThiNDQtZThlNzViM2ExMjE2Iiwia2lkIjoiSldULVNpZ25hdHVyZS1LZXkiLCJzdWIiOiJDSEFSQUNURVI6RVZFOjkzODEzMzEwIiwiYXpwIjoiNjgzMDg0YWI1Zjg4NDhkNGIxODc0NjJhYzNiOTc2NzciLCJ0ZW5hbnQiOiJ0cmFucXVpbGl0eSIsInRpZXIiOiJsaXZlIiwicmVnaW9uIjoid29ybGQiLCJhdWQiOlsiNjgzMDg0YWI1Zjg4NDhkNGIxODc0NjJhYzNiOTc2NzciLCJFVkUgT25saW5lIl0sIm5hbWUiOiJQZXJpY28gVHVlcnRvIiwib3duZXIiOiJRc2lrT2pXUFFERnAzM1hucEl1VzhnM0Z5eFE9IiwiZXhwIjoxNzExNTMwMjExLCJpYXQiOjE3MTE1MjkwMTEsImlzcyI6Imh0dHBzOi8vbG9naW4uZXZlb25saW5lLmNvbSJ9.cVkBkCgLir-kzahfqxjhjNMmQoks1xbt0zthSvWt0Ynuv-rJhI25m4SGReMJSvnjyseh9bmyblJbXOYEJeF_zdbuyP-KRwshWj4hre-VZ4jJPf4Rl-QcdRxPJ-2hPk-w06ltuDCwWUmaCedQauXg9tHKnM8KGapZ64OENaEKbY4A4ilAS0Iukaz9HqqXEuW7rcGAKXvN27yguF2U_hoN3QzCzGcOB0sLyiW1lpjpOC-vO-1X9nc-RUJGK4bYxoVMtxUL1bBrbBmpp2Rb1A43bpsgNGWQQc-PSzasWw2sNX90oYFHKCGQ7_dRCsk2cT7xgcdaAGjVZe9yaIHTg4Ogzg'

# Mining Operations without Authentication he
curl --request GET --location "http://localhost:3000/nin/v1/character/123/miningoperations"