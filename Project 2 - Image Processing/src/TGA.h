#pragma once
#include <vector>
#include <string>
#include <iostream>
#include <fstream>
using namespace std;
class TGA
{
public:
	struct Header
	{
		char idLength;
		char colorMapType;
		char dataTypeCode;
		short colorMapOrigin;
		short colorMapLength;
		char colorMapDepth;
		short xOrigin;
		short yOrigin;
		short width;
		short height;
		char bitsPerPixel;
		char imageDescriptor;
		int byteCount;
		Header()
		{
			idLength = ' ';
			colorMapType = ' ';
			dataTypeCode = 0;
			colorMapOrigin = 0;
			colorMapLength = ' ';
			colorMapDepth = ' ';
			xOrigin = 0;
			yOrigin = 0;
			width = 0;
			height = 0;
			bitsPerPixel = ' ';
			imageDescriptor = ' ';
			byteCount = 0;

		}
	};
	Header header = Header();
	unsigned char* bytes;
	TGA()
	{
		bytes = nullptr;
	}
	TGA(string filename);
	void Test(TGA example);
	void writeTGA(ofstream& file);
	void multiplyTGA(TGA bot);
	void subtractTGA(TGA bot);
	void screenTGA(TGA bot);
	void overlayTGA(TGA bot);
	void channelTGA(bool option, int channel, float amount);// For option, false = add, true = scale, for channel, 0 = blue, 1 = green, 2 = red
	void parseChannelTGA(int channel);
	void combineTGA(TGA layer, int channel); //for channel, 0 = blue, 1 = green, 2 = red
	void flipTGA();
	void quadTGA(TGA a, TGA b, TGA c);
};