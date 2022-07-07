/*
많은 돈을 지출한 순으로 고객 리스트를 구해주세요.
*/
SELECT Customers.CustomerID
    ,  Customers.CustomerName
    ,  CASE WHEN Orders.CustomerID IS NULL THEN 0
            ELSE Orders.OrderAmount END OrderAmount
  FROM Customers
  LEFT JOIN (
        SELECT Orders.CustomerID
            ,  SUM(OrderDetails.Quantity * Products.Price) OrderAmount
          FROM Orders
         INNER JOIN OrderDetails
            ON Orders.OrderID = OrderDetails.OrderID
         INNER JOIN Products
            ON OrderDetails.ProductID = Products.ProductID
         GROUP BY Orders.CustomerID
    ) Orders
    ON Customers.CustomerID = Orders.CustomerID
 ORDER BY Orders.OrderAmount DESC;
/*

Number of Records: 91
CustomerID	CustomerName	OrderAmount
63	QUICK-Stop	122199.74
71	Save-a-lot Markets	120718.85
20	Ernst Handel	120390.09
37	Hungry Owl All-Night Grocers	60397.91
65	Rattlesnake Canyon Grocery	58562.42
51	Mère Paillarde	36878.50
34	Hanari Carnes	34916.60
62	Queen Cozinha	34043.90
24	Folk och fä HB	33477.95
39	Königlich Essen	32902.62
25	Frankenversand	31794.43
89	White Clover Markets	30417.60
59	Piccolo und mehr	29595.00
5	Berglunds snabbköp	28355.75
76	Suprêmes délices	27918.40
35	HILARIÓN-Abastos	25633.38
9	Bon app'	25360.00
10	Bottom-Dollar Marketse	24429.15
7	Blondel père et fils	22606.05
44	Lehmanns Marktstand	22506.87
68	Richter Supermarkt	21210.45
73	Simons bistro	21062.25
46	LILA-Supermercado	19819.66
32	Great Lakes Food Market	19711.13
72	Seven Seas Imports	18929.15
55	Old World Delicatessen	18563.15
87	Wartian Herkku	18467.30
47	LINO-Delicateses	18429.55
83	Vaffeljernet	17976.82
19	Eastern Connection	16037.75
75	Split Rail Beer & Ale	14718.52
4	Around the Horn	14264.50
67	Ricardo Adocicados	13848.25
14	Chop-suey Chinese	13336.10
23	Folies gourmandes	12263.70
30	Godos Cocina Típica	12143.10
56	Ottilies Käseladen	11709.80
80	Tortuga Restaurante	11667.25
86	Die Wandernde Kuh	11623.55
41	La maison d'Asie	11514.05
84	Victuailles en stock	10823.30
50	Maison Dewey	10430.58
81	Tradição Hipermercados	10013.87
31	Gourmet Lanchonetes	8957.23
49	Magazzini Alimentari Riuniti	8313.35
28	Furia Bacalhau e Frutos do Mar	8025.00
61	Que Delícia	7789.53
66	Reggiani Caseifici	7762.20
60	Princesa Isabel Vinhoss	7647.90
3	Antonio Moreno Taquería	7616.15
88	Wellington Importadora	7085.30
11	B's Beverages	6638.55
38	Island Trading	6429.70
70	Santé Gourmet	6000.35
8	Bólido Comidas preparadas	5543.30
52	Morgenstern Gesundkost	5345.00
21	Familia Arquibaldo	4771.70
58	Pericles Comidas clásicas	4727.16
15	Comércio Mineiro	4637.60
1	Alfreds Futterkiste	4596.20
48	Lonesome Pine Restaurant	4437.10
17	Drachenblut Delikatessend	3896.61
90	Wilman Kala	3727.35
91	Wolski	3646.70
54	Océano Atlántico Ltda.	3540.00
45	Let's Stop N Shop	3490.02
77	The Big Cheese	3446.00
79	Toms Spezialitäten	3302.90
36	Hungry Coyote Import Store	3285.05
6	Blauer See Delikatessen	3239.80
26	France restauration	3172.16
64	Rancho grande	2956.08
74	Spécialités du monde	2423.35
40	La corne d'abondance	1992.05
78	The Cracker Box	1947.24
16	Consolidated Holdings	1916.50
12	Cactus Comidas para llevar	1814.80
33	GROSELLA-Restaurante	1764.60
18	Du monde entier	1683.10
69	Romero y tomillo	1653.58
82	Trail's Head Gourmet Provisioners	1571.20
27	Franchi S.p.A.	1558.36
2	Ana Trujillo Emparedados y helados	1425.15
85	Vins et alcools Chevalier	1205.40
29	Galería del gastrónomo	955.50
53	North/South	649.00
42	Laughing Bacchus Wine Cellars	592.50
43	Lazy K Kountry Store	394.00
13	Centro comercial Moctezuma	126.00
57	Paris spécialités	0.00
22	FISSA Fabrica Inter. Salchichas S.A.	0.00
*/
