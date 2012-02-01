package eu.stratosphere.sopremo.aggregation;

import java.util.ArrayList;
import java.util.List;

import eu.stratosphere.sopremo.EvaluationContext;
import eu.stratosphere.sopremo.type.ArrayNode;
import eu.stratosphere.sopremo.type.IArrayNode;
import eu.stratosphere.sopremo.type.IJsonNode;

public class MaterializingAggregationFunction extends AggregationFunction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3685213903416162250L;

	private transient List<IJsonNode> nodes;

	public MaterializingAggregationFunction() {
		super("<values>");
	}

	protected MaterializingAggregationFunction(final String name) {
		super(name);
	}

	@Override
	public void aggregate(final IJsonNode node, final EvaluationContext context) {
		this.nodes.add(node);
	}

	@Override
	public IJsonNode getFinalAggregate() {
		final IArrayNode arrayNode = new ArrayNode();
		arrayNode.addAll(this.processNodes(this.nodes));
		this.nodes = null;
		return arrayNode;
	}

	@Override
	public void initialize() {
		this.nodes = new ArrayList<IJsonNode>();
	}

	protected List<IJsonNode> processNodes(final List<IJsonNode> nodes) {
		return nodes;
	}
}
