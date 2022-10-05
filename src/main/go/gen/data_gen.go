package gen

import (
	"math/rand"
	"time"
)

type DataGenerator struct {
	studentNumber              int
	lessonNumber               int
	absenceStudentNumber       int
	absenceLessonRate          float64
	randomAbsenceStudentNumber int
}

func (gen *DataGenerator) Generator() [][]int {

	arr := make([][]int, gen.lessonNumber)
	for i := 0; i < len(arr); i++ {
		arr[i] = make([]int, gen.studentNumber)
	}

	absenceStudentArray :=
		rand.Perm(gen.studentNumber)[:gen.absenceStudentNumber]

	absenceLessonNumber :=
		int(0.5 + gen.absenceLessonRate*float64(gen.lessonNumber))
	for _, i := range absenceStudentArray {
		for _, j := range rand.Perm(gen.lessonNumber)[:absenceLessonNumber] {
			arr[j][i] = 1
		}
	}

	for i := 0; i < len(arr); i++ {
		for j := 0; j < gen.randomAbsenceStudentNumber; j++ {
			arr[i][rand.Int()%gen.studentNumber] = 1
		}
	}
	return arr
}

var DefaultDataGenerator = &DataGenerator{
	studentNumber:              90,
	lessonNumber:               20,
	absenceStudentNumber:       7,
	absenceLessonRate:          0.8,
	randomAbsenceStudentNumber: 5,
}

func init() {
	rand.Seed(time.Now().Unix())
}
