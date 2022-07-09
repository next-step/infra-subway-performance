SELECT
    hobby,
    ROUND(COUNT(*) / (SELECT COUNT(*) FROM programmer) * 100, 1) AS percentage
FROM programmer
GROUP BY hobby
ORDER BY hobby DESC;
