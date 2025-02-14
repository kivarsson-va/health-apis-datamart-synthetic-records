package gov.va.api.health.minimartmanager.minimart;

import gov.va.api.health.dataquery.service.controller.allergyintolerance.DatamartAllergyIntolerance;
import gov.va.api.health.dataquery.service.controller.appointment.DatamartAppointment;
import gov.va.api.health.dataquery.service.controller.condition.DatamartCondition;
import gov.va.api.health.dataquery.service.controller.device.DatamartDevice;
import gov.va.api.health.dataquery.service.controller.diagnosticreport.DatamartDiagnosticReport;
import gov.va.api.health.dataquery.service.controller.encounter.DatamartEncounter;
import gov.va.api.health.dataquery.service.controller.immunization.DatamartImmunization;
import gov.va.api.health.dataquery.service.controller.location.DatamartLocation;
import gov.va.api.health.dataquery.service.controller.medication.DatamartMedication;
import gov.va.api.health.dataquery.service.controller.medicationorder.DatamartMedicationOrder;
import gov.va.api.health.dataquery.service.controller.medicationstatement.DatamartMedicationStatement;
import gov.va.api.health.dataquery.service.controller.observation.DatamartObservation;
import gov.va.api.health.dataquery.service.controller.organization.DatamartOrganization;
import gov.va.api.health.dataquery.service.controller.patient.DatamartPatient;
import gov.va.api.health.dataquery.service.controller.practitioner.DatamartPractitioner;
import gov.va.api.health.dataquery.service.controller.practitionerrole.DatamartPractitionerRole;
import gov.va.api.health.dataquery.service.controller.procedure.DatamartProcedure;
import java.util.HashMap;
import java.util.Map;

public class DatamartFilenamePatterns {
  private static final DatamartFilenamePatterns INSTANCE = new DatamartFilenamePatterns();

  private final Map<Class<?>, String> jsonFileRegex;

  private final Map<Class<?>, String> ndjsonFileRegex;

  private DatamartFilenamePatterns() {
    ndjsonFileRegex = new HashMap<>();
    jsonFileRegex = new HashMap<>();
    jsonFileRegex.put(DatamartAllergyIntolerance.class, "^dmAllInt.*json$");
    jsonFileRegex.put(DatamartAppointment.class, "^dmApp.*json$");
    jsonFileRegex.put(DatamartCondition.class, "^dmCon.*json$");
    jsonFileRegex.put(DatamartDevice.class, "^dmDev.*json$");
    jsonFileRegex.put(DatamartDiagnosticReport.class, "^dmDiaRep.*json$");
    jsonFileRegex.put(DatamartEncounter.class, "^dmEnc.*json$");
    jsonFileRegex.put(DatamartImmunization.class, "^dmImm.*json$");
    jsonFileRegex.put(DatamartLocation.class, "^dmLoc.*json$");
    jsonFileRegex.put(DatamartMedication.class, "^dmMed(?!Sta|Ord).*json$");
    jsonFileRegex.put(DatamartMedicationOrder.class, "^dmMedOrd.*json$");
    jsonFileRegex.put(DatamartMedicationStatement.class, "^dmMedSta.*json$");
    jsonFileRegex.put(DatamartObservation.class, "^dmObs.*json$");
    jsonFileRegex.put(DatamartOrganization.class, "^dmOrg.*json$");
    jsonFileRegex.put(DatamartPatient.class, "^dmPat.*json$");
    jsonFileRegex.put(DatamartPractitioner.class, "^dmPra[^R].*json$");
    jsonFileRegex.put(DatamartPractitionerRole.class, "^dmPraRol.*json$");
    jsonFileRegex.put(DatamartProcedure.class, "^dmPro.*json$");
  }

  public static DatamartFilenamePatterns get() {
    return INSTANCE;
  }

  private String getOrDie(Map<Class<?>, String> registry, Class<?> datamartResource) {
    var pattern = registry.get(datamartResource);
    if (pattern == null) {
      throw new IllegalArgumentException(
          "File pattern not found for: " + datamartResource.getName());
    }
    return pattern;
  }

  public String json(Class<?> datamartResource) {
    return getOrDie(jsonFileRegex, datamartResource);
  }

  public String ndjson(Class<?> datamartResource) {
    return getOrDie(ndjsonFileRegex, datamartResource);
  }
}
