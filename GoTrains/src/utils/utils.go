package utils

import "errors"

func Calc(num1,num2 int,operator string) (int,error) {
	switch operator {
	case "+":
		return num1 + num2, nil
	case "-":
		return num1 - num2, nil
	case "*":
		return num1 * num2, nil
	case "/":
		return num1 / num2, nil
	default:
		return 0, errors.New("不合法的运算符")
	}
}