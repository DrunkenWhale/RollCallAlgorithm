from ast import main
from cProfile import label
from matplotlib import pyplot as plt
import os

def read(filePath:str) -> list[float]:
    with open(filePath,"r") as f:
        return list(map(lambda x:float(x),f.readlines()))
    

if __name__ == "__main__":
    size = 10
    linewidth = 3
    prefix = "../res"
    fileList = filter(lambda x:".json" not in x,list(os.listdir(prefix)))
    y =  [read(prefix+os.sep+fileName) for fileName in fileList]
    plt.plot([i for i in range(1,size+1)],y[0],linewidth=linewidth,label="onlyRollCallFrequentlyAbsentStudents")
    plt.plot([i for i in range(1,size+1)],y[1],linewidth=linewidth,label="rollCallStudentsPartlyBasedOnGpa")
    plt.plot([i for i in range(1,size+1)],y[2],linewidth=linewidth,label="knn")
    plt.ylabel("accuracy")
    plt.legend()

    # plt.show()
    plt.savefig("res.jpg")
