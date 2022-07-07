/*
200개 이상 팔린 상품명과 그 수량을 수량 기준 내림차순으로 보여주세요.
*/
SELECT A.ProductID
    ,  A.ProductName
    ,  A.TotalQuantity
  FROM (
        SELECT Products.ProductID
            ,  Products.ProductName
            ,  SUM(OrderDetails.Quantity) TotalQuantity
          FROM Products
         INNER JOIN OrderDetails
            ON Products.ProductID = OrderDetails.ProductID
         GROUP BY Products.ProductID
        HAVING SUM(OrderDetails.Quantity) >= 200
    ) A
 ORDER BY A.TotalQuantity DESC;

/*
Number of Records: 72
ProductID	ProductName	TotalQuantity
60	Camembert Pierrot	1577
59	Raclette Courdavault	1496
31	Gorgonzola Telino	1397
56	Gnocchi di nonna Alice	1263
16	Pavlova	1158
75	Rhönbräu Klosterbier	1155
24	Guaraná Fantástica	1125
40	Boston Crab Meat	1103
62	Tarte au sucre	1083
71	Fløtemysost	1057
2	Chang	1057
21	Sir Rodney's Scones	1016
41	Jack's New England Clam Chowder	981
76	Lakkalikööri	981
17	Alice Mutton	978
55	Pâté chinois	903
13	Konbu	891
51	Manjimup Dried Apples	886
35	Steeleye Stout	883
1	Chais	828
70	Outback Lager	817
72	Mozzarella di Giovanni	806
36	Inlagd Sill	805
68	Scottish Longbreads	799
39	Chartreuse verte	793
77	Original Frankfurter grüne Soße	791
7	Uncle Bob's Organic Dried Pears	763
54	Tourtière	755
33	Geitost	755
26	Gumbär Gummibärchen	753
29	Thüringer Rostbratwurst	746
65	Louisiana Fiery Hot Pepper Sauce	745
10	Ikura	742
64	Wimmers gute Semmelknödel	740
19	Teatime Chocolate Biscuits	723
53	Perth Pasties	722
69	Gudbrandsdalsost	714
11	Queso Cabrales	706
42	Singaporean Hokkien Fried Mee	697
28	Rössle Sauerkraut	640
38	Côte de Blaye	623
30	Nord-Ost Matjeshering	612
61	Sirop d'érable	603
44	Gula Malacca	601
43	Ipoh Coffee	580
23	Tunnbröd	580
46	Spegesild	548
18	Carnarvon Tigers	539
58	Escargots de Bourgogne	534
49	Maxilaku	520
45	Røgede sild	508
34	Sasquatch Ale	506
52	Filo Mix	500
47	Zaanse koeken	485
4	Chef Anton's Cajun Seasoning	453
63	Vegie-spread	445
57	Ravioli Angelo	434
14	Tofu	404
8	Northwoods Cranberry Sauce	372
27	Schoggi Schokolade	365
22	Gustaf's Knäckebröd	348
12	Queso Manchego La Pastora	344
3	Aniseed Syrup	328
25	NuNuCa Nuß-Nougat-Creme	318
20	Sir Rodney's Marmalade	313
6	Grandma's Boysenberry Spread	301
5	Chef Anton's Gumbo Mix	298
74	Longlife Tofu	297
32	Mascarpone Fabioli	297
73	Röd Kaviar	293
66	Louisiana Hot Spiced Okra	239
50	Valkoinen suklaa	235
*/
