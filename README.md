DBS01 dnadolenko

run in cmd or terminal:
1/ mongo.exe connect
2/ use pm
3/ db.createUser({user: "root", pwd: "root", roles: ["readWrite", "dbAdmin" ]})

For data population use this java-app (need previously install JVM).