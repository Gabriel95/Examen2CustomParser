format PE CONSOLE 4.0
entry start
include 'win32a.inc'
section '.data' data readable writeable
str_pause db  'p','a','u','s','e',0
@intprintstr db '%d',10,0
@intscanstr db '%d',0 
@i@ dd 0
temp1 dd 0
section '.code' code readable executable
start:
mov eax,0
mov [@i@],eax
aLoop:
;if1
mov eax,5
cmp [@i@], eax
jl loopBody
jmp outLoop
loopBody:
push [@i@]
push @intprintstr
call [printf]
add esp,8
mov eax,[@i@]
add eax,1
mov [temp1],eax
mov eax,[temp1]
mov [@i@],eax
jmp aLoop
outLoop:

;End Process
push str_pause
call [system]
add esp, 4
call [ExitProcess]

section '.idata' import data readable writeable

library kernel,'KERNEL32.DLL',\
ms ,'msvcrt.dll'

import kernel,\
ExitProcess,'ExitProcess'

import ms,\
printf,'printf',\
cget ,'_getch',\
system,'system',\
scanf,'scanf'