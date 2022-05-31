lab6:
	javac lab6.java

test1:
	java lab6 mem_stream.1 > lab.out
	diff -w -B lab.out mem_stream1.out
	rm lab.out

test2:
	java lab6 mem_stream.2 > lab.out
	diff -w -B lab.out mem_stream2.out
	rm lab.out

noclass: 
	rm *.class

dlab6:
	rm *.class
	javac lab6.java

turnin:
	handin jseng 315_lab6_1 Makefile
	handin jseng 315_lab6_1 *.java