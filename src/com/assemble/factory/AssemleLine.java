package com.assemble.factory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.assemble.entity.Fragments;
import com.assemble.exception.FragmentException;

public class AssemleLine {

	FragmentFactory fragmentFactory = FragmentFactory.getInstance();

	private String reassembleFragments(List<String> val) {
		StringBuilder sb = new StringBuilder();
		sb.append(val.get(0));
		for (String str : val) {
			sb.append(";").append(str);
		}
		return sb.toString();
	}

	public String sortAndAssemble(String fragment, Integer responce, Integer request) {

		String results[] = fragment.split(";");
		ArrayList<String> collectionPayload = new ArrayList<>(Arrays.asList(results));

		String payload = collectionPayload.remove(responce.intValue());
		collectionPayload.add(request, payload);
		return reassembleFragments(collectionPayload);

	}

	private String[] splitValues(String valueToSplit) {
		return valueToSplit.split(";");
	}

	public String reassembleLine(String fragment) {

		String results = defragment(fragment);
		boolean isSorted = false;

		int previousSize = splitValues(results).length;
		int sortedFragments = previousSize - 1;
		if (previousSize > 1) {
			int responce = previousSize - 1;
			int request = 1;
			while (true) {
				results = defragment(fragment);
				if (splitValues(results).length > 1) {
					int currentSize = splitValues(results).length;
					if (currentSize == previousSize) {

						// Exhausted no more matches
						if ((responce == sortedFragments) && (isSorted)) {
							sortedFragments = sortedFragments - 1;
							// Compared everthing to everthing
							if (sortedFragments == 0) {
								break;
							} else {
								responce = sortedFragments;
								request = 1;
							}
						} else {
							if (isSorted) {
								responce = request;
								request = request + 1;
							} else {
								isSorted = true;
							}
						}

						fragment = sortAndAssemble(results, responce, request);
					} else {
						previousSize = currentSize;
						sortedFragments = previousSize - 1;
						responce = previousSize - 1;
						request = 1;
						isSorted = false;
					}

				} else {
					// defragged
					break;
				}
			}
		}
		return results;
	}

	public String defragment(String fragment) {

		StringBuffer buffer = new StringBuffer(fragment);
		String fragments[] = buffer.toString().split(";");
		int currentFragmentsCount = fragments.length;
		int previousFragmentsCount = currentFragmentsCount;

		while (currentFragmentsCount > 1) {
			// writer.println("\"\"\"" + sb.toString() + "\"\"\"");
			// writer.flush();
			String combined = assembleTogetherLargeFragments(buffer.toString());
			buffer = new StringBuffer(combined);
			fragments = buffer.toString().split(";");
			currentFragmentsCount = fragments.length;

			if ((currentFragmentsCount == previousFragmentsCount) && (currentFragmentsCount > 1)) {
				break;
			} else {
				previousFragmentsCount = currentFragmentsCount;
			}

		}
		return buffer.toString();

	}

	public String assembleTogetherLargeFragments(String fragement) {

		List<Fragments> fragments = splitFragmentsIntoPairs(fragement);
		int fragmentCount = LocateTheLongestMatches(fragments);
		Fragments entity = putFragmentsTogether(fragments.get(fragmentCount));
		return fragmentFactory.assembleFactory(fragments, entity, fragement, fragmentCount);
	}

	public Fragments putFragmentsTogether(Fragments fragment) {

		String localFragment = fragment.fragment;
		String localFragmentData = fragment.data;

		String newFragment = "";
		Boolean isFragmentsCombined = fragmentFactory.isFragmentsCombinedCompleted(fragment);
		if (isFragmentsCombined) {

			newFragment = fragmentFactory.isCombine(fragment, localFragment, localFragmentData);
		} else if (fragmentFactory.removeFragment(fragment)) {

			newFragment = fragmentFactory.checkFragmentsLength(localFragment, localFragmentData);
		} else {
			newFragment = "";
		}
		fragment.attachFragments = newFragment;
		// writer.println("mergedvalue:"+frag1+"="+fragmentPair.match.value+"="+frag2+":"+newFrag);
		return fragment;

	}

	

	public int LocateTheLongestMatches(List<Fragments> fragments) {

		int max = 0;
		int count = 0;

		for (int i = 0; i < fragments.size(); i++) {
			Fragments fragment = fragments.get(i);
			if (fragment.match.value.length() > max) {
				if (fragmentFactory.isThereanyValigMatchFound(fragment) || fragmentFactory.removeFragment(fragment)) {
					max = fragment.match.value.length();
					count = i;
				}
			}
		}
		return count;
	}

	public List<Fragments> splitFragmentsIntoPairs(String fragmenttedString) {
		String[] fragments = fragmenttedString.split(";");
		if (fragments.length < 2) {
			throw new FragmentException("cannot perform opperstion");
		}

		List<Fragments> fragmentPairs = splitFragments(fragments);
		return fragmentPairs;

	}

	private List<Fragments> splitFragments(String[] fragments) {
		List<Fragments> fragmentList = new ArrayList<>();
		// writer.println("Frag1,Frag2,Match");
		for (int i = 0; (i + 1) < fragments.length; ++i) {
			Fragments fragment = new Fragments();

			
			fragmentFactory.checkFragmentState(fragments, i, fragment);

			OverLappingFragments overlapping = new OverLappingFragments();
			fragmentList.add(overlapping.FindAllOverLappingFragments(fragment));

		}
		return fragmentList;
	}

	

}
