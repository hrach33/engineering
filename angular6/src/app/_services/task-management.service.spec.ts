import { TestBed, inject } from '@angular/core/testing';

import { TaskManagementService } from './task-management.service';

describe('TaskManagementService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [TaskManagementService]
    });
  });

  it('should be created', inject([TaskManagementService], (service: TaskManagementService) => {
    expect(service).toBeTruthy();
  }));
});
