myData<-read.csv("C:/Users/daniel/Desktop/DAA/datos.csv")
#View(myData)

#Data in different formats
#with an ID variable

myData2<-myData
myData2$ID<-seq.int(nrow(myData2))

#As a matrix
myMatrix<-data.matrix(myData)

#In long format
#install.packages("reshape2")
library(reshape2)
myDataLong<-melt(myData2, id.vars=c("ID"))


#No Friedman test in base, so need a package
#Using the stats package
#install.packages("stats")
library(stats)
#friedman.test(myMatrix)
friedman.test(myDataLong$value,myDataLong$variable,myDataLong$ID)

#Using the agricolae package:
#install.packages("agricolae")
library(agricolae)
friedman(myDataLong$ID,myDataLong$variable,myDataLong$value,console=TRUE)

#Using the coin package
#install.packages("coin")
library(coin)
myDataLong[,'ID']<-factor(myDataLong[,'ID'])
friedman_test(value ~ variable | ID, myDataLong)

#Test de Wilcoxon
#ajuste y correcion false.
pairwise.wilcox.test(myDataLong$value, myDataLong$variable, paired=TRUE, exact=FALSE,p.adj="none",correct=FALSE)



#source("https://bioconductor.org/biocLite.R")
#biocLite("graph")
#biocLite("Rgraphviz")
#install.packages("scmamp")
#library(scmamp)
#multipleComparisonTest(myMatrix,test="friedman")


