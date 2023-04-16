import sys
import copy
from collections import deque

input = sys.stdin.readline

Map = [[[0,0] for _ in range(4)] for _ in range(4)]
result = 0
for i in range(4):
    temp = list(map(int, input().split()))

    for j in range(4):
        Map[i][j][0] = temp[2*j]
        Map[i][j][1] = temp[2*j+1] - 1

dx = [-1, -1, 0, 1, 1, 1, 0, -1]
dy = [0, -1, -1, -1, 0, 1, 1, 1]
def find(cur_Map, ate):
    global result
    mem = [[-1,-1] for _ in range(17)]
    shark = [-1,-1]
    # 순서 기억하기
    for i in range(4):
        for j in range(4):
            if cur_Map[i][j][0] > 0:
                mem[cur_Map[i][j][0]] = [i,j]
            elif cur_Map[i][j][0] == 0:
                shark = [i, j]
    
    for i in range(1, 17): # 물고기 이동 시작
        if mem[i][0]== -1 and mem[i][1]== -1: # 없어진 물고기
            continue
        x = mem[i][0]
        y = mem[i][1]
        cur_dir = cur_Map[x][y][1]
        for j in range(8):
            new_dir = (cur_dir+j)%8
            new_x = x + dx[new_dir]
            new_y = y + dy[new_dir]
            if new_x < 0 or new_x >= 4 or new_y < 0 or new_y >=4 or cur_Map[new_x][new_y][0]==0:
                continue
            cur_Map[x][y][1] = new_dir
            
            if cur_Map[new_x][new_y][0] < 0: # 이동할 위치에 물고기가 없는 경우
                cur_Map[new_x][new_y][0] = cur_Map[x][y][0]
                cur_Map[new_x][new_y][1] = cur_Map[x][y][1]
                cur_Map[x][y][0] = -1 # 기존 위치 빈 위치로 바꾸어주기
                cur_Map[x][y][1] = -1 # 기존 위치 빈 위치로 바꾸어주기
            elif cur_Map[new_x][new_y][0] > 0: # 이동할 위치에 물고기가 있는 경우
                mem[cur_Map[new_x][new_y][0]] = [x, y]
                temp_1 = cur_Map[x][y][0]
                temp_2 = cur_Map[x][y][1]
                cur_Map[x][y][0] = cur_Map[new_x][new_y][0]
                cur_Map[x][y][1] = cur_Map[new_x][new_y][1]
                cur_Map[new_x][new_y][0] = temp_1 
                cur_Map[new_x][new_y][1] = temp_2
                # 위치 바꾸기 완료
            break

    x = shark[0]
    y = shark[1]
    dir = cur_Map[x][y][1]

    for i in range(1, 4): # 상어 이동, x와 y축의 길이가 최대 4이므로
        new_x = x + dx[dir]*i
        new_y = y + dy[dir]*i

        if new_x < 0 or new_x>=4 or new_y < 0 or new_y >= 4 or cur_Map[new_x][new_y][0] < 0:
            result = max(result, ate)
            continue
        new_Map = copy.deepcopy(cur_Map)
        value =  new_Map[new_x][new_y][0]
        new_Map[new_x][new_y][0] = 0
        new_Map[x][y][0] = -1 # 기존 위치 비워주기
        new_Map[x][y][1] = -1 # 기존 위치 비워주기

        find(new_Map, ate+value)
    return



ate = Map[0][0][0]

Map[0][0][0] = 0

find(Map, ate)

print(result)
