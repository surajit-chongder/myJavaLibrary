javac -cp out $(find src -name *.java) && java -cp out/ guestMain/PrintLabel --lastFirst "$@"