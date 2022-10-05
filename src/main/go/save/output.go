package save

import (
	"encoding/csv"
	"os"
)

func WriteToCSV(data [][]string, path string) {
	file, err := os.OpenFile(path, os.O_CREATE|os.O_WRONLY, 0666)
	if err != nil {
		panic(err)
	}
	defer func(file *os.File) {
		err := file.Close()
		if err != nil {
			panic(err)
		}
	}(file)

	csvFile := csv.NewWriter(file)
	err = csvFile.WriteAll(data)
	if err != nil {
		panic(err)
	}
}
