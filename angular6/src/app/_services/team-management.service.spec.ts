import { TestBed, inject } from '@angular/core/testing';

import { TeamManagementService } from './team-management.service';

describe('TeamManagementService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [TeamManagementService]
    });
  });

  it('should be created', inject([TeamManagementService], (service: TeamManagementService) => {
    expect(service).toBeTruthy();
  }));
});
