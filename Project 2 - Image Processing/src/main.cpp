#include "TGA.h"
void firstTask();
void secondTask();
void thirdTask();
void fourthTask();
void fifthTask();
void sixthTask();
void seventhTask();
void eighthTask();
void ninthTask();
void tenthTask();
void extraCreditTask();
int main() 
{
	firstTask();
	secondTask();
	thirdTask();
	fourthTask();
	fifthTask();
	sixthTask();
	seventhTask();
	eighthTask();
	ninthTask();
	tenthTask();
	extraCreditTask();
	return 0;
}
void firstTask()
{
	cout << "First Task" << endl;
	ofstream outFile("output/part1.tga", ios_base::binary);
	TGA top = TGA("input/layer1.tga");
	TGA bottom = TGA("input/pattern1.tga");
	top.multiplyTGA(bottom);
	//Write to file
	top.writeTGA(outFile);
	outFile.close();
	//Running test to confirm equality to example
	top.Test(TGA("examples/EXAMPLE_part1.tga"));
}
void secondTask()
{
	cout << "Second Task" << endl;
	ofstream outFile("output/part2.tga", ios_base::binary);
	TGA top = TGA("input/layer2.tga");
	TGA bottom = TGA("input/car.tga");
	top.subtractTGA(bottom);
	//Write to file
	top.writeTGA(outFile);
	outFile.close();
	//Running test to confirm equality to example
	top.Test(TGA("examples/EXAMPLE_part2.tga"));
}
void thirdTask()
{
	cout << "Third Task" << endl;
	ofstream outFile("output/part3.tga", ios_base::binary);
	TGA top = TGA("input/layer1.tga");
	TGA bottom = TGA("input/pattern2.tga");
	TGA second_top = TGA("input/text.tga");
	top.multiplyTGA(bottom);
	second_top.screenTGA(top);
	//Write to file
	second_top.writeTGA(outFile);
	outFile.close();
	//Running test to confirm equality to example
	second_top.Test(TGA("examples/EXAMPLE_part3.tga"));
}
void fourthTask()
{
	cout << "Fourth Task" << endl;
	ofstream outFile("output/part4.tga", ios_base::binary);
	TGA top = TGA("input/layer2.tga");
	TGA bottom = TGA("input/circles.tga");
	TGA second_top = TGA("input/pattern2.tga");
	top.multiplyTGA(bottom);
	second_top.subtractTGA(top);
	//Write to file
	second_top.writeTGA(outFile);
	outFile.close();
	//Running test to confirm equality to example
	second_top.Test(TGA("examples/EXAMPLE_part4.tga"));
}
void fifthTask()
{
	cout << "Fifth Task" << endl;
	ofstream outFile("output/part5.tga", ios_base::binary);
	TGA top = TGA("input/layer1.tga");
	TGA bottom = TGA("input/pattern1.tga");
	top.overlayTGA(bottom);
	//Write to file
	top.writeTGA(outFile);
	outFile.close();
	//Running test to confirm equality to example
	top.Test(TGA("examples/EXAMPLE_part5.tga"));
}
void sixthTask()
{
	cout << "Sixth Task" << endl;
	ofstream outFile("output/part6.tga", ios_base::binary);
	TGA top = TGA("input/car.tga");
	// For option, false = add, true = scale, for channel, 0 = blue, 1 = green, 2 = red
	top.channelTGA(false, 1, 200);
	//Write to file
	top.writeTGA(outFile);
	outFile.close();
	//Running test to confirm equality to example
	top.Test(TGA("examples/EXAMPLE_part6.tga"));
}
void seventhTask()
{
	cout << "Seventh Task" << endl;
	ofstream outFile("output/part7.tga", ios_base::binary);
	TGA top = TGA("input/car.tga");
	// For option, false = add, true = scale, for channel, 0 = blue, 1 = green, 2 = red
	top.channelTGA(true, 0, 0);
	top.channelTGA(true, 2, 4);
	//Write to file
	top.writeTGA(outFile);
	outFile.close();
	//Running test to confirm equality to example
	top.Test(TGA("examples/EXAMPLE_part7.tga"));
}
void eighthTask()
{
	cout << "Eighth Task" << endl;
	ofstream outFile_b("output/part8_b.tga", ios_base::binary);
	ofstream outFile_r("output/part8_r.tga", ios_base::binary);
	ofstream outFile_g("output/part8_g.tga", ios_base::binary);
	TGA blue = TGA("input/car.tga");
	TGA green = TGA("input/car.tga");
	TGA red = TGA("input/car.tga");
	//for channel, 0 = blue, 1 = green, 2 = red
	blue.parseChannelTGA(0);
	green.parseChannelTGA(1);
	red.parseChannelTGA(2);
	//Write to file
	blue.writeTGA(outFile_b);
	green.writeTGA(outFile_g);
	red.writeTGA(outFile_r);
	outFile_b.close();
	outFile_g.close();
	outFile_r.close();
	//Running test to confirm equality to example
	blue.Test(TGA("examples/EXAMPLE_part8_b.tga"));
	green.Test(TGA("examples/EXAMPLE_part8_g.tga"));
	red.Test(TGA("examples/EXAMPLE_part8_r.tga"));
}
void ninthTask()
{
	cout << "Ninth Task" << endl;
	ofstream outFile("output/part9.tga", ios_base::binary);
	TGA blue = TGA("input/layer_blue.tga");
	TGA green = TGA("input/layer_green.tga");
	TGA red = TGA("input/layer_red.tga");
	//for channel, 0 = blue, 1 = green, 2 = red
	blue.combineTGA(green, 1);
	blue.combineTGA(red, 2);
	//Write to file
	blue.writeTGA(outFile);
	outFile.close();
	//Running test to confirm equality to example
	blue.Test(TGA("examples/EXAMPLE_part9.tga"));
}
void tenthTask()
{
	cout << "Tenth Task" << endl;
	ofstream outFile("output/part10.tga", ios_base::binary);
	TGA flip = TGA("input/text2.tga");
	flip.flipTGA();
	//Write to file
	flip.writeTGA(outFile);
	outFile.close();
	//Running test to confirm equality to example
	flip.Test(TGA("examples/EXAMPLE_part10.tga"));
}
void extraCreditTask()
{
	cout << "Extra Credit Task" << endl;
	ofstream outFile("output/extracredit.tga", ios_base::binary);
	TGA car = TGA("input/car.tga");
	TGA circles = TGA("input/circles.tga");
	TGA pattern = TGA("input/pattern1.tga");
	TGA text = TGA("input/text.tga");
	car.quadTGA(circles, pattern, text);
	//Write to file
	car.writeTGA(outFile);
	outFile.close();
	//Running test to confirm equality to example
	car.Test(TGA("examples/EXAMPLE_extracredit.tga"));
}