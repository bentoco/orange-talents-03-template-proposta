package br.com.zup.proposal.config.health;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.CompositeHealthContributor;
import org.springframework.boot.actuate.health.HealthContributor;
import org.springframework.boot.actuate.health.NamedContributor;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

@Component ( "ProposalAPI" )
public class ProposalAPIHealthContributor implements CompositeHealthContributor {

    private final Map<String, HealthContributor> contributors = new LinkedHashMap<>();

    @Autowired
    public ProposalAPIHealthContributor ( AnalysisResourcesHealthIndicator analysisResourcesHealthIndicator, DataSourceHealthIndicator dataSourceHealthIndicator ) {
        contributors.put("financialEvaluation" , analysisResourcesHealthIndicator);
        contributors.put("database", dataSourceHealthIndicator);
    }

    @Override public HealthContributor getContributor ( String name ) {
        return contributors.get(name);
    }

    /**
     * @return list of health contributors
     */
    @Override public Iterator<NamedContributor<HealthContributor>> iterator () {
        return contributors.entrySet().stream()
                .map(( entry ) -> NamedContributor.of(entry.getKey() , entry.getValue())).iterator();
    }
}
