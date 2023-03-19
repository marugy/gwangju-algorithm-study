import sys
input = sys.stdin.readline

N = int(input())

Map = [[[] for _ in range(N+1)] for _ in range(N+1)]
result = [[0 for _ in range(N+1)] for _ in range(N+1)]


def Find(Values):
    num, one, two, thr, fir = Values
    friends = [one, two, thr, fir]
    result_x = -1
    result_y = -1
    max_f = 0
    emp_f = 0
    dx = [0,1,0,-1]
    dy = [-1,0,1,0]
    check = False
    temp = []
    for i in range(1, N+1):
        for j in range(1, N+1):
            max_t=0
            emp_t =0
            if result[i][j] != 0: continue # 이미 방문했으면 패스
            

            for k in range(4): # 4방 탐색 시작
                new_x = i + dx[k]
                new_y = j + dy[k]
                if 0>=new_x or new_x>N or 0>=new_y or new_y>N: # 범위 외 지역이면 패스
                    continue
                if result[new_x][new_y] in friends: # 옆에 사람이 있고 내가 원하는 친구?
                    max_t += 1
                if result[new_x][new_y] == 0:
                    emp_t +=1
            temp.append((max_t, emp_t, i, j))

    temp.sort(key = lambda x:(-x[0], -x[1], x[2], x[3]))
    result[temp[0][2]][temp[0][3]] = num
    Map[temp[0][2]][temp[0][3]] = friends
    return

for _ in range(N*N):
    Find(map(int, input().split()))

dx = [0,1,0,-1]
dy = [-1,0,1,0]

final_result =0
for i in range(1, N+1):
    for j in range(1, N+1):
        max_t = 0
        for k in range(4): # 4방 탐색 시작
            new_x = i + dx[k]
            new_y = j + dy[k]
            if 0>=new_x or new_x>N or 0>=new_y or new_y>N: # 범위 외 지역이면 패스
                continue
            if result[new_x][new_y] in Map[i][j]: # 옆에 사람이 있고 내가 원하는 친구?
                max_t += 1
        if max_t>0:
            final_result += 10**(max_t-1)
print(final_result)