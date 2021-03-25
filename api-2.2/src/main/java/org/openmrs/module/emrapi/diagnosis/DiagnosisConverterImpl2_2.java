package org.openmrs.module.emrapi.diagnosis;

import org.openmrs.CodedOrFreeText;
import org.openmrs.ConditionVerificationStatus;

import java.util.ArrayList;
import java.util.List;

public class DiagnosisConverterImpl2_2 implements DiagnosisConverter {

//    public List<Diagnosis> convert(List<org.openmrs.Diagnosis> coreDiagnoses) {
//        List<Diagnosis> diagnoses = new ArrayList<Diagnosis>();
//        for (org.openmrs.Diagnosis coreDiagnosis : coreDiagnoses) {
//            diagnoses.add(this.convert(coreDiagnosis));
//        }
//        return diagnoses;
//    }

    //@Override
//    public Diagnosis convert(org.openmrs.Diagnosis coreDiagnosis) {
//        return null;
//    }

    public List<Diagnosis> convert(List<? extends Object> coreDiagnoses) {
        List<Diagnosis> diagnoses = new ArrayList<Diagnosis>();
        for (Object coreDiagnosis2 : coreDiagnoses) {
            org.openmrs.Diagnosis coreDiagnosis = (org.openmrs.Diagnosis)coreDiagnosis2;
            Diagnosis diagnosis = new Diagnosis();
            CodedOrFreeText coded = coreDiagnosis.getDiagnosis();
            diagnosis.setDiagnosis(new CodedOrFreeTextAnswer(coded.getCoded(), coded.getSpecificName(), coded.getNonCoded()));
            diagnosis.setCertainty(coreDiagnosis.getCertainty() == ConditionVerificationStatus.CONFIRMED ? Diagnosis.Certainty.CONFIRMED : Diagnosis.Certainty.PRESUMED);
            diagnosis.setOrder(coreDiagnosis.getRank() == 1 ? Diagnosis.Order.PRIMARY : Diagnosis.Order.SECONDARY);
            diagnoses.add(diagnosis);
        }
        return diagnoses;
    }
}
