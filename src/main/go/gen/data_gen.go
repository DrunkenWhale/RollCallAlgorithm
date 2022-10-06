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

func (gen *DataGenerator) Generator() ([][]int, []float64) {

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

	absenceArray := make([]float64, len(arr[0]))
	for i := 0; i < len(arr[0]); i++ {
		for j := 0; j < len(arr); j++ {
			absenceArray[i] += float64(arr[j][i])
		}
	}
	for i := 0; i < len(absenceArray); i++ {
		absenceArray[i] = _GPA(3-absenceArray[i]*0.1) + 1
	}
	return arr, absenceArray
}

var DefaultDataGenerator = &DataGenerator{
	studentNumber:              90,
	lessonNumber:               20,
	absenceStudentNumber:       7,
	absenceLessonRate:          0.8,
	randomAbsenceStudentNumber: 5,
}

func _GPA(upperBound float64) float64 {
	// 生成 0~上限 的随机绩点
	return rand.Float64() * upperBound
}

func init() {
	rand.Seed(time.Now().Unix())
}
