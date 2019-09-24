package com.assemble.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.assemble.factory.AssemleLine;

public class Execute {
	public static void main(String[] args) throws IOException {
		// writer = new PrintWriter("/tmp/chp.csv", "UTF-8");
		AssemleLine assemble = new AssemleLine();

		BufferedReader in = new BufferedReader(new FileReader("file.txt"));
		String fragmentProblem;
		while ((fragmentProblem = in.readLine()) != null) {
			System.out.println(assemble.reassembleLine(fragmentProblem));
		}

	}
}
