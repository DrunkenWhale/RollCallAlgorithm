package main

import (
	"fmt"
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
	arr, gpa := gen.DefaultDataGenerator.Generator()
	strArr := intArrMapToStringArr(arr)
	gpaArr := floatArrMapToStringArr(gpa)
	save.WriteToCSV(append(
		[][]string{
			gpaArr,
		}, strArr...), path)
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
func floatArrMapToStringArr(arr []float64) []string {
	brr := make([]string, len(arr))
	for i := 0; i < len(arr); i++ {
		brr[i] = fmt.Sprintf("%.3f", arr[i])
	}
	return brr
}
