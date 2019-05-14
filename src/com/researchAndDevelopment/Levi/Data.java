package com.researchAndDevelopment.Levi;

/**
 * This data type stores the data collected from each array analyzed
 * 
 * @param inputSize
 * @param nrOfComparisons
 * @param nrOfAssignments
 */
public class Data {
	private int inputSize;
	private long nrOfComparisons;
	private long nrOfAssignments;
	
	public Data(int inputSize, long nrOfComparisons, long nrOfAssignments) {
		this.inputSize = inputSize;
		this.nrOfComparisons = nrOfComparisons;
		this.nrOfAssignments = nrOfAssignments;
	}

	public int getInputSize() {
		return inputSize;
	}

	public void setInputSize(int inputSize) {
		this.inputSize = inputSize;
	}

	public long getNrOfComparisons() {
		return nrOfComparisons;
	}

	public void setNrOfComparisons(int nrOfComparisons) {
		this.nrOfComparisons = nrOfComparisons;
	}

	public long getNrOfAssignments() {
		return nrOfAssignments;
	}

	public void setNrOfAssignments(int nrOfAssignments) {
		this.nrOfAssignments = nrOfAssignments;
	}

	@Override
	public String toString() {
		return inputSize + " , " + nrOfComparisons + " , " + nrOfAssignments + "\n";
	}

}