#Movemos los .class a BIN

mv $1/SRC/*.class $1/BIN/

#Otra opción por si se generan .class en otra carpeta que no sea /BIN.
#Eso no debería pasar por eso comento la opción. 
#find $1 -type f -name *.class -exec mv {} $1/BIN \;

#Generar la documentación de nuestro proyecto

javadoc -d $1/DOCS -version -author -use $1/SRC/*.java

#Comprimir directorio de la práctica

tar -czvf $1.tar.gz $1
