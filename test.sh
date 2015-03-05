curl -X POST -i -H "Content-type: text/html" http://localhost:8080/robot/hao
curl -X POST -i -H "Content-type: application/json" http://localhost:8080/robot/position/hao -d '{"angle":"WEST", "x":2, "y":2}'
curl -X PUT -i -H "Content-type: application/json" http://localhost:8080/robot/change/hao/left
curl -X GET -i -H  "Content-type: application/json" http://localhost:8080/robot/report/hao
curl -X GET -i -H "Content-type: text/html" http://localhost:8080/robot/list
