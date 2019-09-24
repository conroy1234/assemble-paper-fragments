package com.assemble.factory;

import java.util.List;

import com.assemble.entity.Fragments;

public class FragmentFactory {

	private static FragmentFactory instance;

	private FragmentFactory() {

	}

	public static FragmentFactory getInstance() {
		instance = new FragmentFactory();
		return instance;
	}

	public String assembleFactory(List<Fragments> fragments, Fragments entity, String fragement, int fragmentCount) {
		StringBuilder buffer = new StringBuilder();
		boolean isThereAnyFragment = false;
		for (int i = 0; i < fragments.size(); i++) {
			Fragments currentEntity = fragments.get(i);
			if (i != 0) {
				buffer.append(";");
			}
			if ((fragmentCount == i) && (entity.attachFragments.length() > 0)) {
				buffer.append(entity.attachFragments);
				isThereAnyFragment = true;
			} else {

				if ((!isThereAnyFragment) && ((i + 1) == fragments.size())) {
					buffer.append(currentEntity.fragment).append(";").append(currentEntity.data);
				} else {
					if (isThereAnyFragment) {
						buffer.append(currentEntity.data);
					} else {
						buffer.append(currentEntity.fragment);
					}
				}
			}
		}
		return buffer.toString();
	}

	public boolean isFragmentOneBeginingMatchesTheEndOfFragmentTwo(Fragments fragment) {

		if (fragment.fragment.startsWith(fragment.match.value) && fragment.data.endsWith(fragment.match.value)) {
			return true;
		}

		return false;
	}

	public boolean isFragmentsCombinedEnded(Fragments fragment) {

		if (fragment.fragment.endsWith(fragment.match.value) && fragment.data.startsWith(fragment.match.value)) {
			return true;
		}
		return false;
	}

	public boolean isFragmentsCombinedCompleted(Fragments fragment) {

		if (isFragmentOneBeginingMatchesTheEndOfFragmentTwo(fragment)) {
			return true;
		}

		if (isFragmentsCombinedEnded(fragment)) {
			return true;
		}
		return false;
	}

	public boolean removeFragment(Fragments fragment) {

		if (fragment.match.value.equals(fragment.fragment) || (fragment.match.value.equals(fragment.data))) {
			return true;
		}

		return false;
	}

	public boolean isThereanyValigMatchFound(Fragments fragment) {

		if (isFragmentsCombinedCompleted(fragment)) {
			return true;
		}

		if (removeFragment(fragment)) {
			return true;
		}

		return false;
	}

	public String isCombine(Fragments fragment, String localFragment, String localFragmentData) {
		String newFragment;
		if (isFragmentsCombinedEnded(fragment)) {
			newFragment = localFragment.substring(0, localFragment.length() - fragment.match.value.length());
			newFragment = newFragment + localFragmentData;
		} else {
			newFragment = localFragmentData.substring(0, localFragmentData.length() - fragment.match.value.length());
			newFragment = newFragment + localFragment;
		}
		return newFragment;
	}

	public void checkFragmentState(String[] fragments, int i, Fragments fragment) {
		if (i == 0) {
			fragment.fragment = fragments[0];
			fragment.data = fragments[1];
		} else {
			fragment.fragment = fragments[i];
			fragment.data = fragments[i + 1];

		}
	}

	public String checkFragmentsLength(String localFragment, String localFragmentData) {
		String newFragment;
		if (localFragment.length() > localFragmentData.length()) {
			newFragment = localFragment;
		} else if (localFragmentData.length() > localFragment.length()) {
			newFragment = localFragmentData;
		} else {
			newFragment = localFragmentData;
		}
		return newFragment;
	}
}
