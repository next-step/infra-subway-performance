/*
많이 주문한 순으로 고객 리스트(ID, 고객명)를 구해주세요. (고객별 구매한 물품 총 갯수)
*/
SELECT Customers.CustomerID
    ,  Customers.CustomerName
    ,  CASE WHEN Orders.CustomerID IS NULL THEN 0
            ELSE SUM(OrderDetails.Quantity) END OrderQuantity
  FROM Customers
  LEFT JOIN Orders
    ON Customers.CustomerID = Orders.CustomerID
  LEFT JOIN OrderDetails
    ON Orders.OrderID = OrderDetails.OrderID
 GROUP BY Customers.CustomerID
 ORDER BY SUM(OrderDetails.Quantity) DESC;

/*
많이 주문한 순으로 고객 리스트(ID, 고객명)를 구해주세요. (고객별 구매한 물품 총 갯수)
*/
SELECT Customers.CustomerID
    ,  Customers.CustomerName
    ,  CASE WHEN Orders.CustomerID IS NULL THEN 0
            ELSE Orders.OrderQuantity END OrderQuantity
  FROM Customers
  LEFT JOIN (
        SELECT Orders.CustomerID
            ,  SUM(OrderDetails.Quantity) OrderQuantity
          FROM Orders
         INNER JOIN OrderDetails
            ON Orders.OrderID = OrderDetails.OrderID
         GROUP BY Orders.CustomerID
    ) Orders
    ON Customers.CustomerID = Orders.CustomerID
 ORDER BY Orders.OrderQuantity DESC;

/*
Number of Records: 91
CustomerID	CustomerName	OrderQuantity
71	Save-a-lot Markets	4958
20	Ernst Handel	4543
63	QUICK-Stop	3961
37	Hungry Owl All-Night Grocers	1684
25	Frankenversand	1525
65	Rattlesnake Canyon Grocery	1383
24	Folk och fä HB	1234
35	HILARIÓN-Abastos	1096
76	Suprêmes délices	1072
89	White Clover Markets	1063
62	Queen Cozinha	1031
5	Berglunds snabbköp	1001
9	Bon app'	980
47	LINO-Delicateses	970
51	Mère Paillarde	966
10	Bottom-Dollar Marketse	956
39	Königlich Essen	903
34	Hanari Carnes	839
46	LILA-Supermercado	836
72	Seven Seas Imports	818
68	Richter Supermarkt	810
44	Lehmanns Marktstand	794
83	Vaffeljernet	792
87	Wartian Herkku	737
7	Blondel père et fils	666
67	Ricardo Adocicados	660
4	Around the Horn	650
55	Old World Delicatessen	635
59	Piccolo und mehr	624
19	Eastern Connection	569
56	Ottilies Käseladen	537
86	Die Wandernde Kuh	492
14	Chop-suey Chinese	465
41	La maison d'Asie	442
84	Victuailles en stock	434
49	Magazzini Alimentari Riuniti	433
30	Godos Cocina Típica	395
61	Que Delícia	394
80	Tortuga Restaurante	384
73	Simons bistro	378
3	Antonio Moreno Taquería	359
21	Familia Arquibaldo	357
23	Folies gourmandes	354
28	Furia Bacalhau e Frutos do Mar	349
32	Great Lakes Food Market	345
66	Reggiani Caseifici	335
75	Split Rail Beer & Ale	327
50	Maison Dewey	320
31	Gourmet Lanchonetes	315
81	Tradição Hipermercados	300
38	Island Trading	295
11	B's Beverages	293
88	Wellington Importadora	267
60	Princesa Isabel Vinhoss	254
58	Pericles Comidas clásicas	208
91	Wolski	205
79	Toms Spezialitäten	204
8	Bólido Comidas preparadas	190
45	Let's Stop N Shop	181
90	Wilman Kala	175
1	Alfreds Futterkiste	174
52	Morgenstern Gesundkost	172
70	Santé Gourmet	161
17	Drachenblut Delikatessend	160
6	Blauer See Delikatessen	140
48	Lonesome Pine Restaurant	134
15	Comércio Mineiro	133
54	Océano Atlántico Ltda.	132
36	Hungry Coyote Import Store	122
12	Cactus Comidas para llevar	115
64	Rancho grande	92
69	Romero y tomillo	91
82	Trail's Head Gourmet Provisioners	89
16	Consolidated Holdings	87
40	La corne d'abondance	83
18	Du monde entier	80
85	Vins et alcools Chevalier	71
26	France restauration	69
2	Ana Trujillo Emparedados y helados	63
42	Laughing Bacchus Wine Cellars	62
78	The Cracker Box	59
27	Franchi S.p.A.	54
74	Spécialités du monde	48
77	The Big Cheese	46
29	Galería del gastrónomo	42
33	GROSELLA-Restaurante	34
53	North/South	30
43	Lazy K Kountry Store	20
13	Centro comercial Moctezuma	11
22	FISSA Fabrica Inter. Salchichas S.A.	0
57	Paris spécialités	0
*/
