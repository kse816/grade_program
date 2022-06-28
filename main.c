#include <stdio.h>
#include <stdlib.h>
#include <memory.h>
#pragma warning(disable:4996)
#define MAX_SIZE 100
#define SU 3

struct student_info {
    char name[10];
    int id; int gender;
    int c; int java; int kotlin;
    int total; double avr; char grade;
};
int total_number = 0;
typedef struct student_info STU;
void input_info(STU* student);
char get_grade(double avr);
void output_info(STU* student);
void search_info(STU* student);
void sort_info(STU* student);
void insert_info(STU* student);
int range_check(char title[], int min, int max);
void delete_info(STU* student);
void update_info(STU* student);

int main()
{
    printf("학생 수를 입력하세요 : ");
    scanf("%d", &total_number);
    printf("\n");
    STU student[MAX_SIZE];
    while (1) {
        int menu = 0;
        printf("----------MENU--------- \n");
        printf("1. 성적 입력(자동) \n");
        printf("2. 성적 출력 \n");
        printf("3. 성적 검색 \n");
        printf("4. 성적 정렬 \n");
        printf("5. 성적 삽입 \n");
        printf("6. 성적 삭제 \n");
        printf("7. 성적 수정 \n");
        printf("8. 나가기 \n");
        printf("----------------------- \n");
        printf("원하시는 번호를 누르세요 : ");
        scanf("%d", &menu);
        switch (menu) {
        case 1: input_info(student); getchar(); getchar(); system("cls"); break;
        case 2: output_info(student); getchar(); getchar(); system("cls"); break;
        case 3: search_info(student); getchar(); getchar(); system("cls"); break;
        case 4: sort_info(student); getchar(); getchar(); system("cls"); break;
        case 5: insert_info(student); getchar(); getchar(); system("cls"); break;
        case 6: delete_info(student); getchar(); getchar(); system("cls"); break;
        case 7: update_info(student); getchar(); getchar(); system("cls"); break;
        case 8: printf("\n프로그램이 종료되었습니다. "); return;
        default: printf("올바른 번호를 입력하세요."); break;
        }
    }
    return 0;
}
//성적 입력 (자동)
void input_info(STU* student) {
    int i = 0;
    for (i = 0; i < total_number; i++) {
        student[i].name[0] = rand() % 26 + 'A';
        student[i].name[1] = rand() % 26 + 'A';
        student[i].name[2] = rand() % 26 + 'A';
        student[i].name[3] = NULL;
        student[i].id = rand() % (9999 - 1000 + 1) + 1000;
        student[i].gender = rand() % 2;

        student[i].c = rand() % 101;
        student[i].java = rand() % 101;
        student[i].kotlin = rand() % 101;

        student[i].total = student[i].c + student[i].java + student[i].kotlin;
        student[i].avr = student[i].total / (double)SU;
        student[i].grade = get_grade(student[i].avr);
    }
    printf("입력 완료하였습니다. \n");

    return;
}
//등급 구하기
char get_grade(double avr) {
    char grade = 0;
    if (avr >= 90.0) {
        grade = 'A';
    }
    else if (avr >= 80.0 && avr < 90.0) {
        grade = 'B';
    }
    else if (avr >= 70.0 && avr < 80.0) {
        grade = 'C';
    }
    else if (avr >= 60.0 && avr < 70.0) {
        grade = 'D';
    }
    else {
        grade = 'F';
    }
    return grade;
}
//성적 출력
void output_info(STU* student) {
    int i = 0;
    for (i = 0; i < total_number; i++) {
        printf("%2d) %5s %5d %5s %3d %3d %3d %d %7.2lf %5c \n",
            i + 1, student[i].name, student[i].id, (student[i].gender == 1) ? "남" : "여", student[i].c, student[i].java, student[i].kotlin, student[i].total, student[i].avr, student[i].grade);
    }

    return;
}
//성적 검색
void search_info(STU* student) {
    char name[10] = "";
    printf("검색할 이름을 입력하세요 : ");
    scanf("%s", name);
    int i = 0; int find_flag = 0;
    for (i = 0; i < total_number; i++) {
        if (!strnicmp(&student[i].name[0], &name[0], 3)) {
            printf("%d) %5s %5d %5s : %3d %3d %3d -> %d %7.2lf %5c \n",
                i + 1, student[i].name, student[i].id, (student[i].gender == 1) ? "남" : "여",
                student[i].c, student[i].java, student[i].kotlin, student[i].total, student[i].avr, student[i].grade);
            find_flag = 1;
            return;
        }
    }
    printf("잘못 입력하셨습니다. 다시 입력하세요 \n");
    return;
}
//성적 정렬
void sort_info(STU* student) {
    STU copy_student[MAX_SIZE];
    STU buffer_student;
    int i, j = 0;
    for (i = 0; i < total_number; i++) {
        memcpy(&copy_student[i], &student[i], sizeof student[i]);
    }
    int count = 0;
    printf("오름차순 : 1, 내림차순 : 2  \n번호 : ");
    scanf("%d", &count);
    if (count == 1) {
        for (i = 0; i < total_number - 1; i++) {
            for (j = i + 1; j < total_number; j++) {
                if (copy_student[i].total > copy_student[j].total) {
                    memcpy(&buffer_student, &copy_student[i], sizeof copy_student[i]);
                    memcpy(&copy_student[i], &copy_student[j], sizeof copy_student[i]);
                    memcpy(&copy_student[j], &buffer_student, sizeof copy_student[i]);
                }
            }
        }
        output_info(copy_student);
        return;
    }
    else if (count == 2) {
        for (i = 0; i < total_number - 1; i++) {
            for (j = i + 1; j < total_number; j++) {
                if (copy_student[i].total < copy_student[j].total) {
                    memcpy(&buffer_student, &copy_student[i], sizeof copy_student[i]);
                    memcpy(&copy_student[i], &copy_student[j], sizeof copy_student[i]);
                    memcpy(&copy_student[j], &buffer_student, sizeof copy_student[i]);
                }
            }
        }
        output_info(copy_student);
        return;
    }
    else {
        printf("잘못 입력하셨습니다. \n");
    }
    return;
}
//성적삽입
void insert_info(STU* student) {
    char name[10] = "";
    if (total_number > MAX_SIZE) {
        printf("학생 수 초과로 입력할 수 없습니다. ");
        return;
    }

    int flag = 0;
    while (1) {
        printf("입력할 학생 이름 : ");
        scanf("%s", name);
        for (int i = 0; i < total_number; i++) {
            if (!strnicmp(name, student[i].name, 3)) {
                printf("중복된 이름이 있습니다.\n");
                flag = 1;
                break;
            }
        }
        if (flag == 1) {
            flag = 0;
            continue;
        }
        else {
            break;
        }
    }
    total_number++;
    student[total_number - 1].id = range_check("번호", 1000, 9999);
    student[total_number - 1].gender = range_check("성별", 0, 1);
    student[total_number - 1].c = range_check("C 점수", 0, 100);
    student[total_number - 1].java = range_check("java 점수", 0, 100);
    student[total_number - 1].kotlin = range_check("kotlin 점수", 0, 100);
    student[total_number - 1].total = student[total_number - 1].c + student[total_number - 1].java + student[total_number - 1].kotlin;
    student[total_number - 1].avr = student[total_number - 1].total / (double)SU;
    student[total_number - 1].grade = get_grade(student[total_number - 1].avr);

    printf("삽입 완료하였습니다.");

    return;
}
//삽입시 범위 설정
int range_check(char title[], int min, int max) {
    int input_number = 0;
    int flag = 0;
    while (!flag) {
        printf("입력할 학생 %s (%d~%d) : ", title, min, max);
        scanf("%d", &input_number);
        if (input_number >= min && input_number <= max) {
            return input_number;
        }
        else { printf("다시 입력하세요 \n"); }
    }
    return input_number;
}
//성적 삭제
void delete_info(STU* student) {
    char name[10] = "";
    printf("삭제할 이름을 입력하세요 : ");
    scanf("%s", name);
    int i = 0; int find_flag = 0;
    for (i = 0; i < total_number; i++) {
        if (!strnicmp(&student[i].name[0], &name[0], 3)) {
            student[i].name[0] = "";
            student[i].name[1] = "";
            student[i].name[2] = "";

            student[i].id = 0;
            student[i].gender = 0;
            student[i].c = 0;
            student[i].java = 0;
            student[i].kotlin = 0;
            student[i].total = 0;
            student[i].avr = 0.0;
            student[i].grade = 0;

            find_flag = 1;
            printf("삭제 완료하였습니다.");
            return;
        }
    }
    printf("잘못 입력하셨습니다. 다시 입력하세요 \n");

    return;
}
//성적 수정
void update_info(STU* student) {
    char name[10] = "";
    printf("수정할 이름을 입력하세요 : ");
    scanf("%s", name);
    int i = 0; int find_flag = 0;
    for (i = 0; i < total_number; i++) {
        if (!strnicmp(&student[i].name[0], &name[0], 3)) {
            printf("수정할 C점수(기존 %d) : ", student[i].c);
            scanf("%d", &student[i].c);
            printf("수정할 java점수(기존 %d) : ", student[i].java);
            scanf("%d", &student[i].java);
            printf("수정할 C점수(기존 %d) : ", student[i].kotlin);
            scanf("%d", &student[i].kotlin);
            student[i].total = student[i].c + student[i].java + student[i].kotlin;
            student[i].avr = student[i].total / (double)SU;
            student[i].grade = get_grade(student[i].avr);
            printf("수정 완료하였습니다 \n");
        }
    }
    output_info(student);
    return;
}