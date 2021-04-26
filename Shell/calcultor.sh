#!/bin/bash
#
# 模拟计算器，支持加减乘除运算


# 计算结果
result=0

#######
# 加法
#######
function add() {
    result=$(( $1 + $2 ))
}

#######
# 乘法
#######
function multiply() {
    result=$(( $1 * $2 ))
}

#######
# 除法
#######
function divide() {
    if [[ "$2" == 0 ]]; then
        echo "error: divisor is 0!"
        exit 1
    fi
    result=$(awk 'BEGIN{printf "%.2f\n", '"$1"'/'"$2"'}')
}

#######
# 减法
#######
function subtract() {
    result=$(( $1 - $2))
}


echo "support addition, subtraction, multiplication, division."
echo -n "please input num1:"
read -r num1
echo -n "please input num2:"
read -r num2
echo -n "please input operation:"
read -r operation

case "${operation}" in
  +)
    add "${num1}" "${num2}"
    ;;
  -)
    subtract "${num1}" "${num2}"
    ;;
  \*)
    multiply "${num1}" "${num2}"
    ;;
  /)
    divide "${num1}" "${num2}"
    ;;
  *)
    echo "error: operation is invalid!"
    exit 1
esac

echo "result is ${result}"
