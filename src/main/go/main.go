package main

import (
	"gen/gen"
	"gen/save"
	"os"
	"strconv"
)

var dir = "../../../data"

func main() {
	if _, err := os.ReadDir(dir); err != nil {
		// dir unexist
		err = os.Mkdir(dir, 0666)
		if err != nil {
			panic(err)
		}
	}
	for i := 1; i <= 5; i++ {
		generateDataAndSave(dir + string(os.PathSeparator) + "lesson" + strconv.Itoa(i) + ".csv")
	}
}

func generateDataAndSave(path string) {
	arr := gen.DefaultDataGenerator.Generator()
	strArr := intArrMapToStringArr(arr)
	save.WriteToCSV(strArr, path)
}

func intArrMapToStringArr(arr [][]int) [][]string {
	brr := make([][]string, len(arr))
	for i := 0; i < len(arr); i++ {
		brr[i] = make([]string, len(arr[0]))
		for j := 0; j < len(arr[0]); j++ {
			brr[i][j] = strconv.Itoa(arr[i][j])
		}
	}
	return brr
}
