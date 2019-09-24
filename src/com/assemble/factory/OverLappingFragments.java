package com.assemble.factory;
import com.assemble.entity.Fragments;
import com.assemble.entity.Match;

public class OverLappingFragments {
	public Fragments FindAllOverLappingFragments(Fragments fragmentPair) {

		Integer[][] matchingMatrix = buildMatchingMatrix(
				fragmentPair.fragment, fragmentPair.data);
		Match match = findLongestMatchesLocation(matchingMatrix);

		if (match.maxValue > 0) {
			match.value = getMatchingString(match, fragmentPair.fragment);
		} else {
			match.value = "";
		}
		fragmentPair.match = match;
		return fragmentPair;
	}

	public String getMatchingString(Match match, String target) {
		int currLoc = match.xvalue;
		StringBuilder subString = new StringBuilder();
		for (int i = 0; i < match.maxValue; i++) {
			subString.append(target.charAt(currLoc));
			currLoc = currLoc - 1;
		}
		return subString.reverse().toString();
	}

	public Match findLongestMatchesLocation(Integer[][] payload) {

		int maxVal = 0;
		Match match = new Match();
		for (int i = 0; i < payload.length; i++) {
			for (int j = 0; j < payload[0].length; j++) {
				Integer val = payload[i][j];
				if (val > maxVal) {
					match.xvalue = i;
					match.yvalue = j;
					match.maxValue = val;
					maxVal = val;
				}
			}
		}

		return match;
	}

	public Integer[][] buildMatchingMatrix(String target, String data) {
		if ((target == null) || (target.length() < 1) || (data == null)
				|| (data.length() < 1)) {
			return null;
		}

		Integer[][] matchMatrix = new Integer[target.length()][data.length()];
		for (int i = 0; i < target.length(); i++) {
			for (int j = 0; j < data.length(); j++) {
				if (target.charAt(i) != data.charAt(j)) {
					matchMatrix[i][j] = 0;
				} else {
					if ((i == 0) || (j == 0))
						matchMatrix[i][j] = 1;
					else
						matchMatrix[i][j] = 1 + matchMatrix[i - 1][j - 1];

				}
			}
		}
		return matchMatrix;
	}

}
