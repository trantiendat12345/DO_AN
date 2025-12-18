import { ref } from 'vue'

export interface Student {
  id: number
  name: string
  class: string
  email: string
  status: string
}

export function useStudents() {
  const students = ref<Student[]>([
    { id: 1, name: 'Nguyễn An', class: 'KIDS A1', email: 'an@mail.com', status: 'Đang học' },
    { id: 2, name: 'Trần Bình', class: 'TEENS B2', email: 'binh@mail.com', status: 'Tạm nghỉ' }
  ])

  const selectedStudent = ref<Student | null>(null)

  function addStudent(student: Omit<Student, 'id'>) {
    students.value.push({
      id: Date.now(),
      ...student
    })
  }

  function deleteStudent(id: number) {
    students.value = students.value.filter(s => s.id !== id)
  }

  function selectStudent(student: Student) {
    selectedStudent.value = { ...student }
  }

  function updateStudent() {
    if (!selectedStudent.value) return

    const index = students.value.findIndex(
      s => s.id === selectedStudent.value!.id
    )

    if (index !== -1) {
      students.value[index] = { ...selectedStudent.value }
    }
  }

  return {
    students,
    selectedStudent,
    addStudent,
    deleteStudent,
    selectStudent,
    updateStudent
  }
}

